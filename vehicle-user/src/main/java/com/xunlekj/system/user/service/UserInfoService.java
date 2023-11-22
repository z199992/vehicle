package com.xunlekj.system.user.service;

import com.querydsl.core.types.Predicate;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.system.user.model.dto.UserDetail;
import com.xunlekj.system.user.model.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> findUserInfoByAccount(String account);

    Page<UserInfo> getAllUserInfo(Predicate predicate, Pageable pageable);

    Page<UserDetail> getAllUserDetail(Predicate predicate, Pageable pageable);

    Optional<UserInfo> findUserInfoById(String id);

    UserInfo saveUserInfo(UserInfo userInfo);

    boolean existsUserByAccount(String userId, String account);

    boolean existsUserByEmail(String userId, String email);

    boolean existsUserByPhone(String userId, String phone);

    List<String> getRoles(UserInfo userInfo);

    Map<Module, OperationType> getModuleOperationTypeMap(UserInfo userInfo);
}
