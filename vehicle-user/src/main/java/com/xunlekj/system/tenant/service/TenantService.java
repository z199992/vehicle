package com.xunlekj.system.tenant.service;

import com.xunlekj.system.tenant.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TenantService {
    Page<Tenant> findAll(Pageable pageable);

    List<Tenant> findAll();

    Tenant save(Tenant tenant);

    void disable(String id);
}
