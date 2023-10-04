package com.xunlekj.security.service;

import com.xunlekj.system.user.model.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    String login(String account, String password) throws Exception;

    boolean addUser(UserInfo userInfo);
}
