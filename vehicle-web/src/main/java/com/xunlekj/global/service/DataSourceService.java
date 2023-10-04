package com.xunlekj.global.service;

import liquibase.exception.LiquibaseException;

public interface DataSourceService {
    void createDatabase(String tenantId);

    void addTenantDataSourceToRouting(String tenantId) throws LiquibaseException;
}
