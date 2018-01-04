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

public class DataBaseConfig {
/*
    @Value("postgres://localhost:5432/postgres")
    private String databaseUrl;

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        URI dbUri = new URI(databaseUrl);

        String username = "postgres";
        String password = "root";
        String dbUrl = String.format("jdbc:postgresql://%s:%d%s?sslmode=require", dbUri.getHost(), dbUri.getPort(), dbUri.getPath());

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }*/
}
