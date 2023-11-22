package com.xunlekj.module.system.user.model.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xunlekj.system.user.model.entity.enums.UserType;
import jakarta.validation.constraints.NotBlank;

public class AddSuperUserValue extends UserValue{
    @Override
    @NotBlank(message = "账号不能为空")
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    @NotBlank(message = "密码不能为空")
    public String getAccount() {
        return super.getAccount();
    }

    @Override
    @JsonIgnore
    public UserType getType() {
        return super.getType();
    }

    @Override
    @JsonIgnore
    public String getTenantId() {
        return super.getTenantId();
    }


}
