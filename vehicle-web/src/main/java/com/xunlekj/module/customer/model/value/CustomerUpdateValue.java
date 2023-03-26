package com.xunlekj.module.customer.model.value;

import jakarta.validation.constraints.NotBlank;

public class CustomerUpdateValue extends CustomerValue{
    @Override
    @NotBlank(message = "租户ID不能为空")
    public void setId(String id) {
        super.setId(id);
    }
}
