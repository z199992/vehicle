package com.xunlekj.module.system.tenant.model.value;

import jakarta.validation.constraints.NotBlank;

public class TenantUpdateValue extends TenantValue {
    @Override
    @NotBlank(message = "租户ID不能为空")
    public String getId() {
        return super.getId();
    }
}
