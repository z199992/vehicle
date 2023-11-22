package com.xunlekj.module.authentication.controller;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.module.authentication.model.value.LoginInfo;
import com.xunlekj.module.system.user.model.mapper.UserMapper;
import com.xunlekj.module.system.user.model.view.CurrentUserInfo;
import com.xunlekj.module.system.user.service.UserManageService;
import com.xunlekj.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserManageService userManageService;


    @Operation(
            summary = "登录",
            tags = "Authentication"
    )
    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestBody @Valid LoginInfo loginInfo) throws Exception {
        String token = userService.login(loginInfo.getAccount(), loginInfo.getPassword());
        return Collections.singletonMap("token", token);
    }

    @Operation(
            summary = "获取当前登录用户信息",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Authentication"
    )
    @GetMapping("/user-info")
    @PreAuthorize("isAuthenticated()")
    public CurrentUserInfo getCurrentUserInfo(@AuthenticationPrincipal User user) {
        return userManageService.getCurrentUserInfo(user.getUserId());
    }
}
