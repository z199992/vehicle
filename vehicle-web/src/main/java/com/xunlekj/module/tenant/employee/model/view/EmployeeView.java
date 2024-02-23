package com.xunlekj.module.tenant.employee.model.view;

import com.xunlekj.module.system.user.model.view.UserView;
import com.xunlekj.module.tenant.employee.model.value.EmployeeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeView extends EmployeeValue {
    private UserView user;
}
