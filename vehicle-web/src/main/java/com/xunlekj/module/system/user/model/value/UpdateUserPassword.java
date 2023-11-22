package com.xunlekj.module.system.user.model.value;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserPassword {
    @NotBlank(message = "用戶ID不能為空")
    private String userId;
    @NotBlank(message = "密碼不能為空")
    private String password;
}
