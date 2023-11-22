package com.xunlekj.module.system.user.service;

import com.querydsl.core.types.Predicate;
import com.xunlekj.exception.NotFoundDataException;
import com.xunlekj.module.system.user.model.view.CurrentUserInfo;
import com.xunlekj.module.system.user.model.view.UserView;
import com.xunlekj.system.user.model.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.naming.NoPermissionException;

public interface UserManageService {
    UserInfo addUser(String operationUserId, UserInfo userInfo) throws NoPermissionException;

    UserInfo updateUserBaseInfo(String operationUserId, UserInfo userInfo) throws NoPermissionException, NotFoundDataException;

    UserInfo findExistUserInfoById(String userId) throws NotFoundDataException;

    CurrentUserInfo getCurrentUserInfo(String userId);

    Page<UserView> findAllUser(Predicate predicate, String operationUserId, Pageable pageable) throws NoPermissionException;

    void updateUserPassword(String operationId, String userId, String password) throws NotFoundDataException, NoPermissionException;
}
