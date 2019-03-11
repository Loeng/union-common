package com.union.druid.druid;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


/**
 * @author GaoWei
 * @time 2018/6/22 16:48
 * @description
 */
@Data
@ConfigurationProperties
public class MongoDataConfig {

    @Value(value = "${spring.data.mongodb.uri}")
    private String uri;



    @Bean
    @Primary
    private MongoProperties mongoProperties(){
        MongoProperties mongoProperties=new MongoProperties();
        mongoProperties.setUri(uri);
        return mongoProperties;
    }


}
