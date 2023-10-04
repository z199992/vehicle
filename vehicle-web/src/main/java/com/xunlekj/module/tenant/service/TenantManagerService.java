package com.xunlekj.module.tenant.service;

import com.xunlekj.system.tenant.model.Tenant;
import liquibase.exception.LiquibaseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TenantManagerService {
    void addTenant(Tenant tenant) throws LiquibaseException;

    void updateTenant(Tenant tenant);

    void loadAllTenantDataSource();

    void disableTenant(String id);

    Page<Tenant> findAll(Pageable pageable);
}
