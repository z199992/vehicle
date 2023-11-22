package com.xunlekj.system.user.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xunlekj.system.user.model.dto.UserDetail;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.repository.UserInfoDetailRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.Optional;

import static com.xunlekj.system.user.model.entity.QUserInfo.userInfo;
import static com.xunlekj.system.tenant.model.QTenant.tenant;

public class UserInfoDetailRepositoryImpl implements UserInfoDetailRepository {
    @PersistenceContext(unitName = "system")
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    private Querydsl querydsl;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
        querydsl = new Querydsl(entityManager, new PathBuilderFactory().create(UserInfo.class));;
    }

    @Override
    public Page<UserDetail> findAllUserDetail(Predicate predicate, Pageable pageable) {
        JPAQuery<UserDetail> query = queryFactory.select(getDTO())
                .from(userInfo)
                .leftJoin(tenant).on(userInfo.tenantId.eq(tenant.id))
                .where(predicate);
        return PageableExecutionUtils.getPage(querydsl.applyPagination(pageable, query).fetch(), pageable, query::fetchCount);
    }

    @Override
    public Optional<UserDetail> findUserDetail(Predicate predicate) {
        return Optional.ofNullable(queryFactory.select(getDTO())
                .from(userInfo)
                .leftJoin(tenant).on(userInfo.tenantId.eq(tenant.id))
                .where(predicate)
                .fetchOne());
    }

    private static QBean<UserDetail> getDTO() {
        return Projections.bean(UserDetail.class,
                userInfo.id,
                userInfo.account,
                userInfo.password,
                userInfo.nickName,
                userInfo.phone,
                userInfo.email,
                userInfo.type,
                userInfo.expireTime,
                userInfo.credentialsExpireTime,
                userInfo.locked,
                userInfo.enable,
                userInfo.tenantId,
                userInfo.createTime,
                userInfo.updateTime,
                tenant.as("tenant")
        );
    }
}
