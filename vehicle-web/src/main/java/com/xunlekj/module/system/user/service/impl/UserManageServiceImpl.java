package com.xunlekj.module.system.user.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.xunlekj.exception.CommitDataException;
import com.xunlekj.exception.NotFoundDataException;
import com.xunlekj.module.system.user.model.mapper.UserMapper;
import com.xunlekj.module.system.user.model.view.CurrentUserInfo;
import com.xunlekj.module.system.user.model.view.UserView;
import com.xunlekj.module.system.user.service.UserManageService;
import com.xunlekj.system.user.model.entity.QUserInfo;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.model.entity.enums.UserType;
import com.xunlekj.system.user.service.UserInfoService;
import com.xunlekj.tenant.employee.model.entity.Employee;
import com.xunlekj.tenant.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
@Slf4j
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoService service;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo addUser(String operationUserId, UserInfo userInfo) throws NoPermissionException {
        checkUserInfo(operationUserId, userInfo);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        log.info("添加用户: {}", userInfo);
        return service.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo updateUserBaseInfo(String operationUserId, UserInfo userInfo) throws NoPermissionException, NotFoundDataException {
        UserInfo userInfoInData = findExistUserInfoById(userInfo.getId());
        userInfo = userMapper.updateBaseInfo(userInfoInData, userInfo);
        checkUserInfo(operationUserId, userInfo);
        userInfo = service.saveUserInfo(userInfo);
        log.info("更新用户信息: {}", userInfo);
        return userInfo;
    }

    @Override
    public UserInfo findExistUserInfoById(String userId) throws NotFoundDataException {
        return service.findUserInfoById(userId).orElseThrow(() -> new NotFoundDataException("用户不存在!"));
    }

    private void checkUserInfo(String operationUserId, UserInfo userInfo) throws NoPermissionException {
        if(service.existsUserByAccount(userInfo.getId(), userInfo.getAccount())) {
            throw new CommitDataException("账号已存在!");
        }

        if(service.existsUserByEmail(userInfo.getId(), userInfo.getEmail())) {
            throw new CommitDataException("邮箱已存在!");
        }

        if(service.existsUserByPhone(userInfo.getId(), userInfo.getPhone())) {
            throw new CommitDataException("手机号已存在!");
        }

        checkOperationUser(operationUserId, userInfo);
    }

    private void checkOperationUser(String operationUserId, UserInfo userInfo) throws NoPermissionException {
        UserInfo operationUser = service.findUserInfoById(operationUserId).orElseThrow();
        switch (operationUser.getType()) {
            case Administrator:
                break;
            case SystemSuperUser:
                if(UserType.Administrator.equals(userInfo.getType()) || UserType.SystemSuperUser.equals(userInfo.getType())) {
                    throw new NoPermissionException("无权操作!");
                }
                break;
            case TenantManager:
                if(!UserType.User.equals(userInfo.getType()) || !operationUser.getTenantId().equals(userInfo.getTenantId())) {
                    throw new NoPermissionException("无权操作!");
                }
                break;
            default:
                throw new NoPermissionException("无权操作!");
        }
    }

    @Override
    public CurrentUserInfo getCurrentUserInfo(String userId) {
        UserInfo userInfo = service.findUserInfoById(userId).orElseThrow();
        Employee employee = null;
        if(UserType.User.equals(userInfo.getType()) && StringUtils.isNotBlank(userInfo.getTenantId())) {
            employee = employeeService.findByUserId(userId);
        }
        return userMapper.toCurrentUserInfo(userInfo, employee, service.getRoles(userInfo), service.getModuleOperationTypeMap(userInfo));
    }

    @Override
    public Page<UserView> findAllUser(Predicate predicate, String operationUserId, Pageable pageable) throws NoPermissionException {
        UserInfo userInfo = service.findUserInfoById(operationUserId).orElseThrow();
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(predicate);

        switch (userInfo.getType()) {
            case Administrator:
                break;
            case SystemSuperUser:
                builder.and(QUserInfo.userInfo.type.ne(UserType.Administrator));
                builder.and(QUserInfo.userInfo.type.ne(UserType.SystemSuperUser));
                break;
            default:
                throw new NoPermissionException("无权操作!");
        }

        return service.getAllUserDetail(builder, pageable).map(userMapper::toView);
    }

    @Override
    public void updateUserPassword(String operationId, String userId, String password) throws NotFoundDataException, NoPermissionException {
        UserInfo userInfo = service.findUserInfoById(userId).orElseThrow(() -> new NotFoundDataException("用户不存在!"));
        checkOperationUser(operationId, userInfo);
        userInfo.setPassword(passwordEncoder.encode(password));
        service.saveUserInfo(userInfo);
    }

    @Override
    public List<UserInfo> findAll(Predicate predicate) {
        return service.findAll(predicate);
    }
}
