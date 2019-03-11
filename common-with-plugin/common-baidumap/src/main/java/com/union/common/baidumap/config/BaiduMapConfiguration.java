package com.union.common.baidumap.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties({BaiduProperties.class})
@ConditionalOnClass({BaiduParams.class})
@ConditionalOnProperty(prefix="com.unioninfo.baidumap", value={"enabled"}, matchIfMissing=true)
public class BaiduMapConfiguration
{
    @Resource
    private BaiduProperties baiduProperties;

    @Bean
    @ConditionalOnMissingBean({BaiduParams.class})
    public BaiduParams aliPayParams()
    {
        BaiduParams baiduParams = new BaiduParams();
        baiduParams.setAk(this.baiduProperties.getAk());
        return baiduParams;
    }
}