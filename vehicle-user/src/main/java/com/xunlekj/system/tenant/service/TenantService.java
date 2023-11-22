package com.xunlekj.system.tenant.service;

import com.querydsl.core.types.Predicate;
import com.xunlekj.system.tenant.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TenantService {
    Page<Tenant> findAll(Pageable pageable);

    List<Tenant> findAll();

    List<Tenant> findAllBy(Predicate predicate);

    Tenant save(Tenant tenant);

    void disable(String id);

    Page<Tenant> findBy(Predicate predicate, Pageable pageable);
}
