package com.xunlekj.security.service.impl;

import com.xunlekj.security.model.dto.UserImpl;
import com.xunlekj.security.service.UserService;
import com.xunlekj.user.model.entity.UserInfo;
import com.xunlekj.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userinfo = userInfoService.findUserInfoByAccount(username);
        UserImpl user = new UserImpl(userinfo.orElseThrow(() -> new UsernameNotFoundException("")));
        return user;
    }
}
