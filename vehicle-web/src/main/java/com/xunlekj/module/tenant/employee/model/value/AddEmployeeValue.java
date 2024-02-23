package com.xunlekj.module.tenant.employee.model.value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddEmployeeValue extends EmployeeValue {
    private AddUserValue user;

    @Override
    @JsonIgnore
    public Integer getId() {
        return super.getId();
    }
}
