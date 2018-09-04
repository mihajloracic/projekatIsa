package com.example.demo.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

//dodati anotaciju configuration kad bude trebalo na heroku
@Configuration
public class DataBaseConfig {

    @Value("postgres://uoxsqoldyunplm:f7fb9c9b6b3380880d516b7ab7ac1e09eedd3ba66bff9085255022ab54ab4803@ec2-184-73-240-228.compute-1.amazonaws.com:5432/dcbbld1uo8r77o")
    private String databaseUrl;

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        URI dbUri = new URI(databaseUrl);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = String.format("jdbc:postgresql://%s:%d%s?sslmode=require", dbUri.getHost(), dbUri.getPort(), dbUri.getPath());

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}