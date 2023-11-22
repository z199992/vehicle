package com.xunlekj.tenant.employee.service.impl;

import com.xunlekj.tenant.employee.model.entity.Employee;
import com.xunlekj.tenant.employee.model.entity.QEmployee;
import com.xunlekj.tenant.employee.repository.EmployeeRepository;
import com.xunlekj.tenant.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override

    public Employee findByUserId(String userId) {
        return repository.findOne(QEmployee.employee.userId.eq(userId)).orElse(null);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
