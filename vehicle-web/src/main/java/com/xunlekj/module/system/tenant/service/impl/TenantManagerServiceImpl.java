package com.xunlekj.module.system.tenant.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.xunlekj.global.service.DataSourceService;
import com.xunlekj.module.system.tenant.service.TenantManagerService;
import com.xunlekj.system.tenant.model.QTenant;
import com.xunlekj.system.tenant.model.Tenant;
import com.xunlekj.system.tenant.service.TenantService;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public Page<Tenant> findBy(Predicate predicate, Pageable pageable) {
        return service.findBy(predicate, pageable);
    }

    @Override
    public Page<Tenant> findByKeys(Pageable pageable, List<String> keys) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(keys != null && !keys.isEmpty()) {
            keys.forEach(key -> booleanBuilder.and(createPredicateByKey(key)));
        }
        return findBy(booleanBuilder, pageable);
    }

    @Override
    public List<Tenant> findAllByName(String name) {
        return service.findAllBy(QTenant.tenant.name.likeIgnoreCase("%" + name + "%"));
    }

    private Predicate createPredicateByKey(String key) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(QTenant.tenant.name.likeIgnoreCase("%" + key + "%"));
        booleanBuilder.or(QTenant.tenant.phone.likeIgnoreCase("%" + key + "%"));
        booleanBuilder.or(QTenant.tenant.email.likeIgnoreCase("%" + key + "%"));
        return booleanBuilder;
    }

}
