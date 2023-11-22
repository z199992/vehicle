package com.xunlekj.module.system.user.controller;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.exception.CommitDataException;
import com.xunlekj.exception.NotFoundDataException;
import com.xunlekj.module.system.user.model.condition.QueryUserCondition;
import com.xunlekj.module.system.user.model.mapper.UserMapper;
import com.xunlekj.module.system.user.model.value.AddSuperUserValue;
import com.xunlekj.module.system.user.model.value.AddTenantManager;
import com.xunlekj.module.system.user.model.value.UpdateUserPassword;
import com.xunlekj.module.system.user.model.value.UpdateUserValue;
import com.xunlekj.module.system.user.model.view.UserView;
import com.xunlekj.module.system.user.service.UserManageService;
import com.xunlekj.security.anotation.CheckModule;
import com.xunlekj.security.service.UserService;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.model.entity.enums.UserType;
import com.xunlekj.system.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;

@RestController
@RequestMapping("/system/user")
@Slf4j
public class UserController {
    @Autowired
    private UserInfoService service;
    @Autowired
    private UserManageService userManageService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Operation(
            summary = "獲取全部用戶",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @GetMapping("/list/all")
    @CheckModule(module = Module.SystemUser, operation = OperationType.View)
    public Page<UserView> allUserList(@AuthenticationPrincipal User user, @ParameterObject QueryUserCondition condition, @ParameterObject Pageable pageable) throws NoPermissionException {
        return userManageService.findAllUser(condition.getPredicate(), user.getUserId(), pageable);
    }

    @Operation(
            summary = "添加管理员用戶",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @CheckModule(module = Module.SystemUser, operation = OperationType.Add)
    @PostMapping(value = "/add/superuser")
    public boolean addSuperUser(@AuthenticationPrincipal User user, @RequestBody @Valid AddSuperUserValue value) throws NoPermissionException {
        UserInfo userInfo = userMapper.toEntity(value);
        userInfo.setType(UserType.SystemSuperUser);
        userManageService.addUser(user.getUserId(), userInfo);
        log.info("add super user: {}, operator: {}", userInfo.getAccount(), user.getUserId());
        return true;
    }

    @Operation(
            summary = "添加租户管理用戶",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @CheckModule(module = Module.SystemUser, operation = OperationType.Add)
    @PostMapping(value = "/add/tenant-manager")
    public boolean addTenantManager(@AuthenticationPrincipal User user, @RequestBody @Valid AddTenantManager value) throws NoPermissionException {
        UserInfo userInfo = userMapper.toEntity(value);
        userInfo.setType(UserType.TenantManager);
        userManageService.addUser(user.getUserId(), userInfo);
        log.info("add tenant manager user: {}, operator: {}", userInfo.getAccount(), user.getUserId());
        return true;
    }

    @Operation(
            summary = "修改用戶基本信息",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @CheckModule(module = Module.SystemUser, operation = OperationType.Update)
    @PostMapping(value = "/update/info")
    public boolean updateUser(@AuthenticationPrincipal User user, @RequestBody @Valid UpdateUserValue userValue) throws NoPermissionException, NotFoundDataException {
        if (user.getUserId().equals(userValue.getId())) {
            throw new CommitDataException("不能修改自己的資料!");
        }

        UserInfo userInfo = userMapper.toEntity(userValue);
        userManageService.updateUserBaseInfo(user.getUserId(), userInfo);
        log.info("update user: {}, operator: {}", userInfo.getAccount(), user.getUserId());
        return true;
    }

    @Operation(
            summary = "修改用戶密碼",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @CheckModule(module = Module.SystemUser, operation = OperationType.Update)
    @PostMapping(value = "/update/password")
    public boolean updateUserPassword(@AuthenticationPrincipal User user, @RequestBody @Valid UpdateUserPassword updateUserPassword) throws NoPermissionException, NotFoundDataException {
        userManageService.updateUserPassword(user.getUserId(), updateUserPassword.getUserId(), updateUserPassword.getPassword());
        log.info("update user password: {}, operator: {}", updateUserPassword.getUserId(), user.getUserId());
        return true;
    }

    @Operation(
            summary = "判断账号是否存在",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @GetMapping(value = "/check/account")
    public boolean checkUserAccount(String userId, String account) {
        return service.existsUserByAccount(userId, account);
    }

    @Operation(
            summary = "判断邮箱是否存在",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @GetMapping(value = "/check/email")
    public boolean checkUserEmail(String userId, String email) {
        return service.existsUserByEmail(userId, email);
    }

    @Operation(
            summary = "判断手机号是否存在",
            security = @SecurityRequirement(name = "jwt"),
            tags = "System UserInfo"
    )
    @GetMapping(value = "/check/phone")
    public boolean checkUserPhone(String userId, String phone) {
        return service.existsUserByPhone(userId, phone);
    }
}
