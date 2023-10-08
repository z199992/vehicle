package com.xunlekj.security.service;

import com.xunlekj.exception.LoginException;
import com.xunlekj.system.user.model.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    String login(String account, String password) throws Exception;

    boolean addUser(UserInfo userInfo);

    void checkUserCanLogin(UserInfo userInfo) throws LoginException;

    void checkUserCanLogin(UserDetails user) throws LoginException;
}
