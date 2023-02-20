package com.xunlekj.user.service.impl;

import com.xunlekj.user.model.entity.QUserInfo;
import com.xunlekj.user.model.entity.UserInfo;
import com.xunlekj.user.repository.UserInfoRepository;
import com.xunlekj.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository repository;

    @Override
    public Optional<UserInfo> findUserInfoByAccount(String account) {
        return repository.findOne(QUserInfo.userInfo.account.eq(account));
    }


}
