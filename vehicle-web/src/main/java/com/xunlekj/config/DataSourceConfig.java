package com.xunlekj.config;

import com.xunlekj.config.property.DataSourceProperties;
import com.xunlekj.jpa.VehicleRoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@Data
public class DataSourceConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    @Primary
    public VehicleRoutingDataSource dataSource(@Qualifier("systemDataSource") DataSource systemDataSource) {
        VehicleRoutingDataSource vehicleRoutingDataSource = new VehicleRoutingDataSource();

        vehicleRoutingDataSource.setTargetDataSources(new HashMap<>());
        vehicleRoutingDataSource.setDefaultTargetDataSource(systemDataSource);

        return vehicleRoutingDataSource;
    }

    @Bean("systemDataSource")
    public DataSource systemDataSource() throws LiquibaseException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());

        HikariDataSource systemDataSource = new HikariDataSource(hikariConfig);

        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(systemDataSource);
        springLiquibase.setChangeLog(dataSourceProperties.getSystemChangeLog());
        springLiquibase.setContexts("system");
        springLiquibase.setResourceLoader(resourceLoader);
        springLiquibase.afterPropertiesSet();

        return systemDataSource;
    }


}
