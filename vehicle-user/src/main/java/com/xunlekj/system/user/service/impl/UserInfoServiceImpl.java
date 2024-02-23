package com.xunlekj.system.user.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.xunlekj.constant.ModuleConstants;
import com.xunlekj.constant.RoleConstants;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.system.user.model.dto.UserDetail;
import com.xunlekj.system.user.model.entity.QUserInfo;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.repository.UserInfoRepository;
import com.xunlekj.system.user.service.UserInfoService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository repository;

    @Override
    public Optional<UserInfo> findUserInfoByAccount(String account) {
        return repository.findOne(QUserInfo.userInfo.account.eq(account));
    }

    @Override
    public Page<UserInfo> getAllUserInfo(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @Override
    public Page<UserDetail> getAllUserDetail(Predicate predicate, Pageable pageable) {
        return repository.findAllUserDetail(predicate, pageable);
    }

    @Override
    public Optional<UserInfo> findUserInfoById(String id) {
        return repository.findById(id);
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    @Override
    public boolean existsUserByAccount(String userId, String account) {
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotBlank(userId)) {
            builder.and(QUserInfo.userInfo.id.ne(userId));
        }
        builder.and(QUserInfo.userInfo.account.eq(account));
        return repository.exists(builder);
    }

    @Override
    public boolean existsUserByEmail(String userId, String email) {
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotBlank(userId)) {
            builder.and(QUserInfo.userInfo.id.ne(userId));
        }
        builder.and(QUserInfo.userInfo.email.eq(email));
        return repository.exists(builder);
    }

    @Override
    public boolean existsUserByPhone(String userId, String phone) {
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.isNotBlank(userId)) {
            builder.and(QUserInfo.userInfo.id.ne(userId));
        }
        builder.and(QUserInfo.userInfo.phone.eq(phone));
        return repository.exists(builder);
    }

    @Override
    public List<String> getRoles(UserInfo userInfo) {
        List<String> roles = new ArrayList<>();
        roles.add(RoleConstants.Prefix + userInfo.getType());
        return roles;
    }

    @Override
    public Map<Module, OperationType> getModuleOperationTypeMap(UserInfo userInfo) {
        Stream<Module> moduleStream = Arrays.stream(Module.values());
        switch (userInfo.getType()) {
            case Administrator:
            case SystemSuperUser:
                break;
            case TenantManager:
            case User:
                moduleStream = moduleStream.filter(m -> !ArrayUtils.contains(ModuleConstants.System_Module, m));
                break;
        }

        return moduleStream.collect(Collectors.toMap(m -> m, m -> OperationType.Manager));
    }

    @Override
    public List<UserInfo> findAll(Predicate predicate) {
        return IterableUtils.toList(repository.findAll(predicate));
    }
}
