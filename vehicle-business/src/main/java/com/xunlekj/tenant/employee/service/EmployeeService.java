package com.xunlekj.tenant.employee.service;

import com.querydsl.core.types.Predicate;
import com.xunlekj.tenant.employee.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findByUserId(String userId);

    Page<Employee> findAll(Predicate predicate, Pageable pageable);

    List<Employee> findAll(Predicate predicate);
}
