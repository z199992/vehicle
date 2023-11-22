package com.xunlekj.module.system.user.model.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xunlekj.system.user.model.entity.enums.UserType;
import jakarta.validation.constraints.NotEmpty;

public class UpdateUserValue extends UserValue {
    @Override
    @NotEmpty(message = "用户ID不能为空")
    public String getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    @JsonIgnore
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
