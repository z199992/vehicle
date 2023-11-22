package com.xunlekj.security.service.impl;

import com.xunlekj.exception.LoginException;
import com.xunlekj.security.model.dto.UserImpl;
import com.xunlekj.security.service.JsonWebTokenService;
import com.xunlekj.security.service.UserService;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JsonWebTokenService jsonWebTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = findExistUserInfo(username);
        return new UserImpl(userInfo, userInfoService.getModuleOperationTypeMap(userInfo), userInfoService.getRoles(userInfo));
    }

    @Override
    public String login(String account, String password) throws Exception {
        try {
            UserInfo userInfo = findExistUserInfo(account);

            checkPassword(password, userInfo.getPassword());
            checkUserCanLogin(userInfo);

            return jsonWebTokenService.createToken(account);
        } catch (Exception e) {
            throw new Exception(MessageFormat.format("用户{0}登录失败!", account) + e.getMessage());
        }
    }

    private UserInfo findExistUserInfo(String account) {
        return userInfoService.findUserInfoByAccount(account)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("未找到用户{0}!", account)));
    }

    private void checkPassword(String rawPassword, String encodedPassword) throws Exception {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new Exception("密码错误!");
        }
    }

    /**
     * 判断用户是否可以登录
     */
    @Override
    public void checkUserCanLogin(UserInfo userInfo) throws LoginException {
        if(!Boolean.TRUE.equals(userInfo.getEnable())) {
            throw new LoginException("用户已被禁用!");
        }

        if(Boolean.TRUE.equals(userInfo.getLocked())) {
            throw new LoginException("用户已被锁定!");
        }

        switch (userInfo.getType()) {
            case Administrator:
            case SystemSuperUser:
                break;
            case TenantManager:
            case User:
                if(StringUtils.isNotBlank(userInfo.getTenantId())) {
                    throw new LoginException("用户未分配租户!");
                }
        }
    }

    @Override
    public void checkUserCanLogin(UserDetails user) throws LoginException {
        checkUserCanLogin(((UserImpl) user).getUserInfo());
    }
}
