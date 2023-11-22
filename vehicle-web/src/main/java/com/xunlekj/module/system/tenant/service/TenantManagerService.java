package com.xunlekj.module.system.tenant.service;

import com.querydsl.core.types.Predicate;
import com.xunlekj.system.tenant.model.Tenant;
import liquibase.exception.LiquibaseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TenantManagerService {
    void addTenant(Tenant tenant) throws LiquibaseException;

    void updateTenant(Tenant tenant);

    void loadAllTenantDataSource();

    void disableTenant(String id);

    Page<Tenant> findBy(Predicate predicate, Pageable pageable);

    Page<Tenant> findByKeys(Pageable pageable, List<String> keys);

    List<Tenant> findAllByName(String name);
}
