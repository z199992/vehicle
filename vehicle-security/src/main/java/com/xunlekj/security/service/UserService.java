package com.xunlekj.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    String login(String account, String password) throws Exception;
}
