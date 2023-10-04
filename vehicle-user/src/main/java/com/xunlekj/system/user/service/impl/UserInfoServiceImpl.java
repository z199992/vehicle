package com.xunlekj.system.user.service.impl;

import com.xunlekj.system.user.model.entity.QUserInfo;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.repository.UserInfoRepository;
import com.xunlekj.system.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<UserInfo> getAllUserInfo(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        repository.save(userInfo);
        return true;
    }
}
