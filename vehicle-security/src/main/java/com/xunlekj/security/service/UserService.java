package com.xunlekj.security.service;

import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.exception.LoginException;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.model.entity.enums.UserType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {

    String login(String account, String password) throws Exception;


    void checkUserCanLogin(UserInfo userInfo) throws LoginException;

    void checkUserCanLogin(UserDetails user) throws LoginException;

}
