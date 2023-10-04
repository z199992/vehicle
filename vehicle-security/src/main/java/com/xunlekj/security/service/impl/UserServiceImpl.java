package com.xunlekj.security.service.impl;

import com.xunlekj.constant.ModuleConstants;
import com.xunlekj.constant.RoleConstants;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.security.model.dto.UserImpl;
import com.xunlekj.security.service.JsonWebTokenService;
import com.xunlekj.security.service.UserService;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.service.UserInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userService")
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
        List<String> roles = new ArrayList<>();

        roles.add(RoleConstants.Prefix + userInfo.getType());
        Map<Module, OperationType> moduleOperationTypeMap = getModuleOperationTypeMap(roles);
        return new UserImpl(userInfo, moduleOperationTypeMap, roles);
    }

    @Override
    public String login(String account, String password) throws Exception {
        try {
            UserInfo userInfo = findExistUserInfo(account);
            checkPassword(password, userInfo.getPassword());

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

    private Map<Module, OperationType> getModuleOperationTypeMap(List<String> roles) {
        Stream<Module> moduleStream = Arrays.stream(Module.values());
        if(!CollectionUtils.containsAny(roles, RoleConstants.Prefix + RoleConstants.Administrator, RoleConstants.Prefix + RoleConstants.SystemSuperUser)) {
            moduleStream = moduleStream.filter(m -> !ArrayUtils.contains(ModuleConstants.System_Module, m));
        }
        return moduleStream.collect(Collectors.toMap(m -> m, m -> OperationType.Manager));
    }

    @Override
    public boolean addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoService.addUserInfo(userInfo);
    }
}
