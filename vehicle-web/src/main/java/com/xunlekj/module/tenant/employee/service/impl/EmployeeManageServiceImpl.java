package com.xunlekj.module.tenant.employee.service.impl;

import com.querydsl.core.types.Predicate;
import com.xunlekj.module.system.user.model.mapper.UserMapper;
import com.xunlekj.module.system.user.service.UserManageService;
import com.xunlekj.module.tenant.employee.model.mapper.EmployeeMapper;
import com.xunlekj.module.tenant.employee.model.value.AddEmployeeValue;
import com.xunlekj.module.tenant.employee.model.value.UpdateEmployeeValue;
import com.xunlekj.module.tenant.employee.model.view.EmployeeView;
import com.xunlekj.module.tenant.employee.service.EmployeeManageService;
import com.xunlekj.system.user.model.entity.QUserInfo;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.tenant.employee.model.entity.Employee;
import com.xunlekj.tenant.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeManageServiceImpl implements EmployeeManageService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserManageService userManageService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Page<EmployeeView> findAll(Predicate predicate, Pageable pageable) {
        return toView(employeeService.findAll(predicate, pageable));
    }

    private Page<EmployeeView> toView(Page<Employee> employees) {
        Page<EmployeeView> ret = employees.map(employeeMapper::toView);
        List<String> userIds = employees.get().map(Employee::getUserId).toList();
        Map<String, UserInfo> userMap = userManageService.findAll(QUserInfo.userInfo.id.in(userIds)).stream().collect(Collectors.toMap(UserInfo::getId, userInfo -> userInfo));

        ret.forEach(employeeView -> {
            UserInfo userInfo = userMap.get(employeeView.getUserId());
            employeeView.setUser(userMapper.toView(userInfo));
        });
        return ret;
    }

    @Override
    @Transactional(transactionManager = "systemTransactionManager")
    public void addEmployee(String operationUserId, AddEmployeeValue value) throws NoPermissionException {
        if(value.getUser() != null) {
            this.userManageService.addUser(operationUserId, userMapper.toEntity(value.getUser()));
        }
        this.employeeService.save(employeeMapper.toEntity(value));
    }

    @Override
    public void updateEmployee(UpdateEmployeeValue value) throws NoPermissionException {
        this.employeeService.save(employeeMapper.toEntity(value));
    }


}
