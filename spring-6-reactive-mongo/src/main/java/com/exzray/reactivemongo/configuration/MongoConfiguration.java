package com.exzray.reactivemongo.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

import java.util.Collections;

@Configuration
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "spring";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        super.configureClientSettings(builder);
        builder
                .credential(MongoCredential.createCredential("root", "admin", "password".toCharArray()))
                .applyToClusterSettings(setting -> {
                    setting.hosts(Collections.singletonList(new ServerAddress("127.0.0.1", 27017)));
                });
    }
}