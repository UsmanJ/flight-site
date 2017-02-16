package com.usmanjamil.flightsite.config;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Created by usmanjamil on 16/02/2017.
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "flightsite";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {

        String dbuser = System.getenv("DBUSER");
        String dbpassword = System.getenv("DBPASSWORD");

        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://" + dbuser + ":" + dbpassword + "@ds157158.mlab.com:57158/flightsite");
        return new MongoClient(mongoClientURI);
    }
}
