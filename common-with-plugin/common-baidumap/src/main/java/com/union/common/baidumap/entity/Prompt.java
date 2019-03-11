package com.union.common.baidumap.entity;

/**
 * describe: TODO
 * create_by: houji in 2017/12/7 13:48
 **/
public class Prompt {
    //poi名称
    private String name;
    //poi经纬度
    private String location;
    //poi的唯一标示，ID
    private String uid;
    //城市
    private String city;
    //区县
    private String district;
    //poi分类。默认不召回，若有需求请联系我们
    private String business;
    //poi地址。默认不召回，若有需求请联系我们
    private String cityid;

    private Double lat;//纬度

    private Double lng;//纬度

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
}
