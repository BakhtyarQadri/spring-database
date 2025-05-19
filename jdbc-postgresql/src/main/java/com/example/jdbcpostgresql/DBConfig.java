package com.example.jdbcpostgresql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String USERNAME;

    @Value("${db.password}")
    private String PASSWORD;

    // 1st approach
    @Bean(name = "writeDataSource")
    public DataSource writeDataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        // hikariConfigs.setAutoCommit(false);
        // hikariConfigs.setMaximumPoolSize(5);
        // hikariConfigs.setMinimumIdle(1);
        return new HikariDataSource(config);
    }

    // 2nd approach
    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        return DataSourceBuilder.create()
                .url(DB_URL)
                .username(USERNAME)
                .password(PASSWORD)
                .type(HikariDataSource.class)
                .build();
    }

     // 3rd approach
//     @Bean(name = "writeDataSource")
//     public DataSource writeDataSource() {
//        HikariDataSource ds = new HikariDataSource();
//        ds.setJdbcUrl(DB_URL);
//        ds.setUsername(USERNAME);
//        ds.setPassword(PASSWORD);
//        return ds;
//     }

}
