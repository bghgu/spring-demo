package com.example.demo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import static java.util.Collections.singletonList;

/**
 * Created by ds on 2021/03/14.
 */

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        final MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
        return MongoClients.create(MongoClientSettings.builder()
                .credential(mongoCredential)
                .applyToClusterSettings(settings ->
                        settings.hosts(singletonList(new ServerAddress(host, port)))).build());
    }

}