package com.xunlekj.module.tenant.employee.service;

import com.querydsl.core.types.Predicate;
import com.xunlekj.module.tenant.employee.model.value.AddEmployeeValue;
import com.xunlekj.module.tenant.employee.model.value.UpdateEmployeeValue;
import com.xunlekj.module.tenant.employee.model.view.EmployeeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NoPermissionException;

public interface EmployeeManageService {
    Page<EmployeeView> findAll(Predicate predicate, Pageable pageable);

    @Transactional(transactionManager = "systemTransactionManager")
    void addEmployee(String operationUserId, AddEmployeeValue value) throws NoPermissionException;

    void updateEmployee(UpdateEmployeeValue value) throws NoPermissionException;
}
