package com.xunlekj.security.service.impl;

import com.google.common.collect.Streams;
import com.querydsl.jpa.JPAExpressions;
import com.xunlekj.security.service.RoleService;
import com.xunlekj.user.model.entity.QRole;
import com.xunlekj.user.model.entity.QRoleMapping;
import com.xunlekj.user.model.entity.RoleMapping;
import com.xunlekj.user.repository.RoleMappingRepository;
import com.xunlekj.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private RoleMappingRepository roleMappingRepository;


    @Override
    public List<String> getRolesByUser(String userId) {
        return Streams.stream(getRoleMappingByUserId(userId)).map(RoleMapping::getRoleId).toList();
    }

    public Iterable<RoleMapping> getRoleMappingByUserId(String userId) {
        return roleMappingRepository
                .findAll(
                        QRoleMapping.roleMapping.userId.eq(userId)
                                .and(QRoleMapping.roleMapping.roleId.in(JPAExpressions.select(QRole.role.id).from(QRole.role)))
                );

    }
}
