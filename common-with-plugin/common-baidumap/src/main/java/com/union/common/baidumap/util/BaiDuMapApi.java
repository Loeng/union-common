package com.union.common.baidumap.util;

/**
 * describe: 百度地图-IP定位-返回对象
 * create_by: houji in 2017/12/4 18:14
 **/
public class BaiDuMapApi {
    private String address;// 地址
    private Content content;// 详细内容
    private String status;// 返回状态码

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 内部类-返回内容
     */
    public static class Content{
        private String address;// 简要地址
        private AddressDetail address_detail;// 详细地址信息
        private Point point;// 当前城市中心点，注意当前坐标返回类型

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public AddressDetail getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(AddressDetail address_detail) {
            this.address_detail = address_detail;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    /**
     * 详细地址信息
     */
    public static class AddressDetail{
        private String city;// 城市
        private String city_code;// 百度城市代码
        private String district;// 区县
        private String province;// 省份
        private String street;// 街道
        private String street_number;// 门址

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreet_number() {
            return street_number;
        }

        public void setStreet_number(String street_number) {
            this.street_number = street_number;
        }
    }

    /**
     * 坐标
     */
    public static class Point{
        private String x; // 经度
        private String y; // 纬度

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public Point() {
        }

        public Point(String x, String y) {
            this.x = x;
            this.y = y;
        }
    }
}
