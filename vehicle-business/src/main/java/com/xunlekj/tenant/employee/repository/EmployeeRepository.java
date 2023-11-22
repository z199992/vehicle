package com.xunlekj.tenant.employee.repository;

import com.xunlekj.tenant.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, QuerydslPredicateExecutor<Employee> {
}
