package com.xunlekj.module.tenant.employee.model.mapper;

import com.xunlekj.module.tenant.employee.model.value.AddEmployeeValue;
import com.xunlekj.module.tenant.employee.model.value.UpdateEmployeeValue;
import com.xunlekj.module.tenant.employee.model.view.EmployeeView;
import com.xunlekj.tenant.employee.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    Employee toEntity(AddEmployeeValue value);

    Employee toEntity(UpdateEmployeeValue value);

    EmployeeView toView(Employee employee);
}
