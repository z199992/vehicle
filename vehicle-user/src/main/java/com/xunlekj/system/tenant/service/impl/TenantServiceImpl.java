package com.xunlekj.system.tenant.service.impl;

import com.querydsl.core.types.Predicate;
import com.xunlekj.system.tenant.service.TenantService;
import com.xunlekj.system.tenant.model.Tenant;
import com.xunlekj.system.tenant.repository.TenantRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository repository;

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Tenant> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Tenant> findAllBy(Predicate predicate) {
        return IterableUtils.toList(repository.findAll(predicate));
    }

    @Override
    public Tenant save(Tenant tenant) {
        return repository.save(tenant);
    }

    @Override
    public void disable(String id) {
        Optional<Tenant> tenantOptional = repository.findById(id);
        tenantOptional.ifPresent(tenant -> {
            tenant.setEnable(false);
            repository.save(tenant);
        });
    }

    @Override
    public Page<Tenant> findBy(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }


}
