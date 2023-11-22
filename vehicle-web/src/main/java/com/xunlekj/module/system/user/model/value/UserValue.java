package com.xunlekj.module.system.user.model.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xunlekj.system.user.model.entity.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "用户信息")
public class UserValue {
    private String id;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "手机号码")
    private String phone;
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户类型")
    private UserType type;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Schema(description = "账号过期时间")
//    private LocalDateTime expireTime;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Schema(description = "密码过期时间")
//    private LocalDateTime credentialsExpireTime;

//    @Schema(description = "是否锁定")
//    private Boolean locked = false;
    @Schema(description = "是否启用")
    private Boolean enable = true;

    @Schema(description = "租户ID")
    private String tenantId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateTime;
}
