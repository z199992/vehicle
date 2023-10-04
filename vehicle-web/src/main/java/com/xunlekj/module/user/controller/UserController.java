package com.xunlekj.module.user.controller;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.global.annotations.IsSystemAdministrator;
import com.xunlekj.module.user.annotations.CheckUserModelView;
import com.xunlekj.module.user.model.mapper.UserMapper;
import com.xunlekj.module.user.model.value.AddUserValue;
import com.xunlekj.module.user.model.view.CurrentUserInfo;
import com.xunlekj.module.user.model.view.UserView;
import com.xunlekj.security.service.UserService;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.system.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService service;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Operation(
            summary = "获取当前登录用户信息",
            security = @SecurityRequirement(name = "jwt"),
            tags = "UserInfo"
    )
    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    public CurrentUserInfo getCurrentUserInfo(@AuthenticationPrincipal User user) {
        return userMapper.toCurrentUserInfo(user);
    }

//    @Operation(
//            summary = "獲取全部用戶",
//            security = @SecurityRequirement(name = "jwt"),
//            tags = "UserInfo"
//    )
//    @GetMapping("/list/all")
//    @CheckUserModelView
//    public Page<UserView> allUserList(@ParameterObject Pageable pageable) {
//        return service.getAllUserInfo(pageable).map(userMapper::toView);
//    }
//
//    @Operation(
//            summary = "添加用戶",
//            security = @SecurityRequirement(name = "jwt"),
//            tags = "UserInfo"
//    )
//    @IsSystemAdministrator
//    @PostMapping("/add")
//    public boolean addUser(@RequestBody @Valid AddUserValue value) {
//        UserInfo userInfo = userMapper.toEntity(value);
//        return userService.addUser(userInfo);
//    }

}
