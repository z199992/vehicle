package com.xunlekj.system.user.service;

import com.xunlekj.system.user.model.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> findUserInfoByAccount(String account);

    Page<UserInfo> getAllUserInfo(Pageable pageable);

    boolean addUserInfo(UserInfo userInfo);
}
