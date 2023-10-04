package com.xunlekj.system.tenant.repository;

import com.xunlekj.system.tenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String>, QuerydslPredicateExecutor<Tenant> {
}
