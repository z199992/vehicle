package com.xunlekj.module.tenant.service.impl;

import com.xunlekj.global.service.DataSourceService;
import com.xunlekj.module.tenant.service.TenantManagerService;
import com.xunlekj.system.tenant.model.Tenant;
import com.xunlekj.system.tenant.service.TenantService;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class TenantManagerServiceImpl implements TenantManagerService {
    @Autowired
    private TenantService service;
    @Autowired
    private DataSourceService dataSourceService;

    @Override
    @Transactional(transactionManager = "systemTransactionManager")
    public void addTenant(Tenant tenant) throws LiquibaseException {
        service.save(tenant);
        dataSourceService.createDatabase(tenant.getId());
        dataSourceService.addTenantDataSourceToRouting(tenant.getId());
    }

    @Override
    public void updateTenant(Tenant tenant) {
        service.save(tenant);
    }

    @Override
    public void loadAllTenantDataSource() {
        service.findAll().forEach(tenant -> {
            try {
                dataSourceService.addTenantDataSourceToRouting(tenant.getId());
            } catch (LiquibaseException e) {
                log.error("load tenant data source error, tenant id: {}", tenant.getId(), e);
            }
        });
    }

    @Override
    public void disableTenant(String id) {
        service.disable(id);
    }

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

}
