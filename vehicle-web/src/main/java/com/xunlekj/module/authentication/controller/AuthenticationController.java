package com.xunlekj.module.authentication.controller;

import com.xunlekj.module.authentication.model.value.LoginInfo;
import com.xunlekj.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "登录",
            tags = "Authentication"
    )
    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestBody @Valid LoginInfo loginInfo) throws Exception {
        String token = userService.login(loginInfo.getAccount(), loginInfo.getPassword());
        return Collections.singletonMap("token", token);
    }
}
