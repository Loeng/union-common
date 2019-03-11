package com.union.common.baidumap.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="com.unioninfo.baidumap")
public class BaiduProperties
{
    //百度地图调用密钥
    private String ak;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }
}
