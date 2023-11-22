package com.xunlekj.tenant.employee.service;

import com.xunlekj.tenant.employee.model.entity.Employee;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findByUserId(String userId);
}
