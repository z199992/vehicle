package com.xunlekj.module.user.model.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunlekj.system.user.model.entity.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserValue {
    private String id;

    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String nickName;

    @NotNull(message = "用户类型不能为空")
    private UserType type;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime expireTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime credentialsExpireTime;

    private Boolean locked = false;
    private Boolean enable = true;

    private String tenantId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updateTime;
}
