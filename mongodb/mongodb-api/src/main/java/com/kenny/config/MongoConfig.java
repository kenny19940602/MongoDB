package com.kenny.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * ClassName: MongoConfig
 * Function:  TODO
 * Date:      2020/6/7 13:39
 *
 * @author Kenny
 * version    V1.0
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("127.0.0.1", 27017))))
                        .build());
    }

}
