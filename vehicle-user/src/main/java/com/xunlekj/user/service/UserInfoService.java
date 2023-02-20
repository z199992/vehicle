package com.xunlekj.user.service;

import com.xunlekj.user.model.entity.UserInfo;

import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> findUserInfoByAccount(String account);
}
