package com.neetry.platform.book.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.sql.DataSource;

/**
 * Created by Harutyun Badeyan
 * Date: 01.04.24
 * Time: 01:17
 */
public class TestDataSourceConfig {

    private static final String DATABASE_IMAGE = "mysql:8.3.0";

    @Container
    private static final MySQLContainer<?> container = new MySQLContainer<>(DATABASE_IMAGE);

    @Bean
    public DataSource createDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(container.getUsername());
        dataSourceBuilder.password(container.getPassword());
        dataSourceBuilder.driverClassName(container.getDriverClassName());
        dataSourceBuilder.url(container.getJdbcUrl());
        return dataSourceBuilder.build();
    }

    @PostConstruct
    void start() {
        container.start();
    }

    @PreDestroy
    void stop() {
        container.stop();
    }
}
