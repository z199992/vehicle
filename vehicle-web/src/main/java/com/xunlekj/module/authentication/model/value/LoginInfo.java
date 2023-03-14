package com.xunlekj.module.authentication.model.value;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginInfo {
    @Schema(description = "账号")
    @NotBlank(message = "账号不能为空!")
    private String account;
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空!")
    private String password;
}
