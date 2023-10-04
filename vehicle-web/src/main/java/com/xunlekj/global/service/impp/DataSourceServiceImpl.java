package com.xunlekj.global.service.impp;

import com.xunlekj.config.property.DataSourceProperties;
import com.xunlekj.global.service.DataSourceService;
import com.xunlekj.jpa.VehicleRoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {
    @Autowired
    private VehicleRoutingDataSource dataSource;
    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    @PersistenceContext(unitName = "system")
    private EntityManager entityManager;

    @Override
//    @Transactional
    public void createDatabase(String tenantId) {
//        throw new RuntimeException("Not implemented yet");
        String sql = "CREATE DATABASE " + tenantId;
        entityManager.createNativeQuery(sql).executeUpdate();
        log.info("Create database success, tenant id: {}", tenantId);
    }

    @Override
    public void addTenantDataSourceToRouting(String tenantId) throws LiquibaseException {
        dataSource.addTargetDataSource(tenantId, createTenantDataSource(tenantId));
    }

    private DataSource createTenantDataSource(String tenantId) throws LiquibaseException {
        log.info("Create tenant data source, tenant id: {}", tenantId);
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(dataSourceProperties.getAddress() + tenantId);
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(dataSourceProperties.getTenantChangeLog());
        springLiquibase.setContexts("tenant");
        springLiquibase.setResourceLoader(resourceLoader);
        springLiquibase.afterPropertiesSet();
        log.info("Init tenant data source success, tenant id: {}", tenantId);

        return dataSource;
    }
}
