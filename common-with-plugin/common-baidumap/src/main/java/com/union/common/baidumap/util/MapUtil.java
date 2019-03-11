package com.union.common.baidumap.util;

import com.union.common.baidumap.entity.Prompt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * class_name: ${file_name}
 * package: ${package_name}
 * describe: TODO
 * creat_user: houji
 * creat_date: ${date}
 * creat_time: ${time}
 **/
public class MapUtil {
    private static Logger logger = LoggerFactory.getLogger(MapUtil.class);

    private static final String API_URL_SERVICE = "http://api.map.baidu.com/";// 访问接口的域名

    private static final String API_AK = "cBXn27KKQ3R76nhTrNgl0f7ipTeYAgcU"; // 访问应用的密钥（AK）

    private static final String API_SERVICE_LOCATION = API_URL_SERVICE + "location/ip?ak=" + API_AK + ""; // 根据IP获取当前坐标（接口）

    private static final String API_SERVICE_GEOCODER = API_URL_SERVICE + "geocoder/v2/?ak=" + API_AK + ""; // 根据地址转换经纬度（接口）

    private static final String API_SERVICE_GEOCONV = API_URL_SERVICE + "geoconv/v1/?ak=" + API_AK + ""; // 百度坐标转换（接口）

    private static final String API_SERVICE_SEARCH = API_URL_SERVICE + "place/v2/search?ak="+API_AK+"";  //地址输入提示服务

    private static final String API_SERVICE_PROMPT = API_URL_SERVICE + "place/v2/suggestion?ak="+API_AK+"";  //地址输入提示服务

    /**
     * 行政区划区域检索
     * 根据给出的地址来提示用户所搜索建筑名称、详细地址
     * q(query) 必须，地址  如： 上地、天安、中关、shanghai
     * region  必须，检索行政区划区域（增加区域内数据召回权重，如需严格限制召回数据在区域内，请搭配使用city_limit参数），可输入行政区划名或对应cityCode。目前仅支持全国、省、市三级检索。
     */
    public static JSONArray searchLocation(String query, String region) {
        JSONArray jsonObject = null;
        // 调用百度API，获取当前地理位置，计算当前位置距离商铺多远（km），并且根据位置排序
        try {
            StringBuffer url = new StringBuffer(API_SERVICE_SEARCH);
            url.append("&query=");
            url.append(query);
            url.append("&output=json");
            url.append("&region=");
            url.append(region);
            url.append("&city_limit=true");

            String resultStr = HttpGetClient.send(url.toString());
            JSONObject obj = JSONObject.fromObject(resultStr);
            // 返回状态码
            String status = obj.getString("status");
            if (StringUtils.equals("0",status)) {
                jsonObject = JSONArray.fromObject(obj.getString("results"));
            }
        } catch (Exception e) {
            logger.error("调用百度API提示地址错误:", e);
        } finally {
            return jsonObject;
        }
    }

    /**
     * 根据给出的地址来提示用户所搜索地址
     * q(query) 必须，地址  如： 上地、天安、中关、shanghai
     * region  必须，如：全国、北京市、131、江苏省等
     */
    public static List<Prompt> prompt(String query, String region) {
        List<Prompt> addresslist = new ArrayList<Prompt>();
        try {
            //http://api.map.baidu.com/place/v2/suggestion?query=天安门&region=北京&city_limit=true&output=json&ak=你的ak //GET请求
            StringBuffer url = new StringBuffer(API_SERVICE_PROMPT);
            url.append("&query=");
            url.append(query);
            url.append("&output=json");
            url.append("&region=");
            url.append(region);
            url.append("&city_limit=true");

            String resultStr = HttpGetClient.send(url.toString());
            JSONObject obj = JSONObject.fromObject(resultStr);
            // 返回状态码
            String status = obj.getString("status");
            if (StringUtils.equals("0",status)) {
                JSONArray jsonObject = JSONArray.fromObject(obj.getString("result"));
                if(jsonObject.size()>0){
                    for(int i=0;i<jsonObject.size();i++){
                        JSONObject result = jsonObject.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        Prompt prompt = new Prompt();
                        prompt.setName(result.get("name").toString());
                        prompt.setLocation(result.get("location").toString());
                        JSONObject latlntjson = JSONObject.fromObject(result.get("location").toString());
                        prompt.setLat((Double) latlntjson.get("lat"));
                        prompt.setLng((Double)(latlntjson.get("lng")));
                        prompt.setUid(result.get("uid").toString());
                        prompt.setCity(result.get("city").toString());
                        prompt.setDistrict(result.get("district").toString());
                        prompt.setBusiness(result.get("business").toString());
                        prompt.setCityid(result.get("cityid").toString());
                        addresslist.add(prompt);
                    }
                }
            }
        } catch (Exception e) {
            //logger.error("调用百度API提示地址错误:", e);
            e.printStackTrace();
        } finally {
            return addresslist;
        }
    }

    /**
     * 根据IP获取当前坐标（接口）
     * @param ipStr  可选，IP不出现，或者出现且为空字符串的情况下，会使用当前访问者的IP地址作为定位参数。
     *
     * @return
     */
    public static BaiDuMapApi getLocation(String ipStr) {
        BaiDuMapApi baiDuMapApi = null;
        // 调用百度API，获取当前地理位置，计算当前位置距离商铺多远（km），并且根据位置排序
        try {
            StringBuffer url = new StringBuffer(API_SERVICE_LOCATION);
            /**
             * bd09ll:百度经纬度坐标;
             * gcj02:国测局坐标
             */
            url.append("&coor=bd09ll");
            /**
             * IP参数
             */
            if(StringUtils.isNotBlank(ipStr) && !StringUtils.equals("127.0.0.1",ipStr)){
                url.append("&ip=")
                        .append(ipStr);
            }
            String resultStr = HttpGetClient.send(url.toString());
            baiDuMapApi = (BaiDuMapApi) JSONObject.toBean(JSONObject.fromObject(resultStr), BaiDuMapApi.class);
            // 返回状态码
            if (!StringUtils.equals("0", baiDuMapApi.getStatus())) {
                // 状态码不能等于 0 时，表示定位失败
                baiDuMapApi = null;
            }
        } catch (Exception e) {
            //logger.error("调用百度API获取地位位置错误:", e);
            e.printStackTrace();
        } finally {
            return baiDuMapApi;
        }
    }

    /**
     * 地址到经纬度坐标转换接口
     * @param address 地址 例如：科技园科技中三路生产力大楼
     * @return
     */
    public static BaiDuMapApi.Point geocoder(String address){
        BaiDuMapApi.Point point = null;
        try {
            StringBuffer url = new StringBuffer(API_SERVICE_GEOCODER);
            if(StringUtils.isBlank(address)){
                throw new Exception("地址不能为空！");
            }else{
                url.append("&output=json")
                        .append("&address=")
                        .append(address);
            }

            String resultStr = HttpGetClient.send(url.toString());
            JSONObject obj = JSONObject.fromObject(resultStr);
            // 返回状态码
            String status = obj.getString("status");
            if(StringUtils.equals("0",status)){
                obj = obj.getJSONObject("result").getJSONObject("location");
                point = new BaiDuMapApi.Point();
                point.setX(obj.getString("lat"));
                point.setY(obj.getString("lng"));
            }
        } catch (Exception e) {
            //logger.error("调用百度API转换地址到坐标错误:", e);
            e.printStackTrace();
        } finally {
            return point;
        }
    }

    /**
     * 百度坐标转换（接口）最多转换100个
     *
     * @param points 坐标集合(X经度,Y纬度)
     * @param from 原坐标格式
     * @param to 转换后的格式
     * @return
     */
    public static List<BaiDuMapApi.Point> geoconv(List<BaiDuMapApi.Point> points, Integer from, Integer to) {
        List<BaiDuMapApi.Point> pointList = null;

        // 调用百度API，获取当前地理位置，计算当前位置距离商铺多远（km），并且根据位置排序
        try {
            //http://api.map.baidu.com/geoconv/v1/?coords=113.9307,22.51514&from=3&to=5&ak=yVPuXg3xe5HsfL4GATHakhXcmip1XmO1
            StringBuffer url = new StringBuffer(API_SERVICE_GEOCONV);
            /**
             * 1：GPS设备获取的角度坐标，wgs84坐标;
             * 2：GPS获取的米制坐标、sogou地图所用坐标;
             * 3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局坐标;
             * 4：3中列表地图坐标对应的米制坐标;
             * 5：百度地图采用的经纬度坐标;
             * 6：百度地图采用的米制坐标;
             * 7：mapbar地图坐标;
             * 8：51地图坐标
             */
            url.append("&from=");
            url.append(from == null ? 3 : from);
            /**
             * 5：bd09ll(百度经纬度坐标);
             * 6：bd09mc(百度米制经纬度坐标);
             */
            url.append("&to=");
            url.append(to == null ? 5 : to);
            /**
             * 经格式：经度,纬度;经度,纬度…
             * 限制：最多支持100个
             * 格式举例：
             * 114.21892734521,29.575429778924;
             * 114.21892734521,29.575429778924
             */
            url.append("&coords=");
            for (int i = 0; i < points.size(); i++) {
                if(i==0){
                    url.append(points.get(i).getX()+","+points.get(i).getY());
                }else{
                    url.append(";"+points.get(i).getX()+","+points.get(i).getY());
                }
            }
            String resultStr = HttpGetClient.send(url.toString());
            JSONObject obj = JSONObject.fromObject(resultStr);
            // 返回状态码
            String status = obj.getString("status");
            if (StringUtils.equals("0",status)) {
                pointList = (List<BaiDuMapApi.Point>) JSONArray.toCollection(JSONArray.fromObject(obj.getString("result")), BaiDuMapApi.Point.class);
            }
        } catch (Exception e) {
            //logger.error("调用百度API转换坐标错误:", e);
            e.printStackTrace();
        } finally {
            return pointList;
        }
    }

    /**
     * GPS设备获取的角度坐标，wgs84坐标转换为百度坐标
     *
     * @param point 坐标集合(X经度,Y纬度)
     * @return
     */
    public static BaiDuMapApi.Point appTobaidu(BaiDuMapApi.Point point) {
        List<BaiDuMapApi.Point> points = new ArrayList<>();
        points.add(point);
        List<BaiDuMapApi.Point> result = geoconv(points,1,5);
        return result == null ? null : result.get(0);
    }

    /**
     * 计算两点之间距离
     * @param start 开始坐标
     * @param end 结束坐标
     * @return 千米（保留两位小数）
     */
    public static double getDistance(BaiDuMapApi.Point start,BaiDuMapApi.Point end){
        double pk = 180 / 3.14169;
        double a1 = Double.parseDouble(start.getY()) / pk;
        double a2 = Double.parseDouble(start.getX()) / pk;
        double b1 = Double.parseDouble(end.getY()) / pk;
        double b2 = Double.parseDouble(end.getX()) / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        BigDecimal bg = new BigDecimal(6371000 * tt);
        return bg.divide(new BigDecimal(1000),2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 测试
    public static void main(String[] args) {
       /* BaiDuMapApi.Point point = new BaiDuMapApi.Point();
        point.setX("114.02597366");
        point.setY("22.54605355");
        BaiDuMapApi.Point point1 = new BaiDuMapApi.Point();
        point1.setX("113.93724571877");
        point1.setY("22.520835174091");
        System.out.println(getDistance(point,point1));*/
        // 117.136.40.186 （手机）
        // 183.14.135.175 （公司）
        // 广东省深圳市福田区农轩路55号
        // System.out.println(MapUtis.getLocation("183.14.135.175").getContent().getPoint().getX());
        // System.out.println(MapUtis.getLocation("183.14.135.175").getContent().getPoint().getY());
        // System.out.println(geocoder("深圳市南山科技园中区生产力大楼bc栋四楼408").getX());
        /*BaiDuMapApi.Point point = new BaiDuMapApi.Point();
        point.setX("114.057868");
        point.setY("22.543099");*/
//        point.setX("113.938");
//        point.setY("22.54878");
        // 113.949425,22.551416
        // x:114.057868
        // y:22.543099
        /*point = appTobaidu(point);*/
        /*System.out.println(point.getX()+","+point.getY());*/
        prompt("城市天地","深圳市");
    }
}
