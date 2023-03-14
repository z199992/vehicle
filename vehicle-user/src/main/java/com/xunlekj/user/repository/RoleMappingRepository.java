package com.xunlekj.user.repository;

import com.xunlekj.user.model.entity.RoleMapping;
import com.xunlekj.user.model.entity.RoleMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMappingRepository extends JpaRepository<RoleMapping, RoleMappingId>, QuerydslPredicateExecutor<RoleMapping> {
}
