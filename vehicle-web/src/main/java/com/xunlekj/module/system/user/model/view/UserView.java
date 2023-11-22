package com.xunlekj.module.system.user.model.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xunlekj.module.system.user.model.value.UserValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserView extends UserValue {
    private String tenantName;

    @Override
    @JsonIgnore
    public String getPassword() {
        return super.getPassword();
    }
}
