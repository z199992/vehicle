package com.xunlekj.module.tenant.employee.controller;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.module.tenant.employee.model.condition.QueryEmployeeCondition;
import com.xunlekj.module.tenant.employee.model.view.EmployeeView;
import com.xunlekj.security.anotation.CheckModule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Operation(
            summary = "獲取全部员工信息",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Employee"
    )
    @GetMapping("/list/all")
    @CheckModule(module = Module.Employee, operation = OperationType.View)
    public Page<EmployeeView> allEmployeeList(@AuthenticationPrincipal User user, @ParameterObject QueryEmployeeCondition condition, @ParameterObject Pageable pageable) {
        //        return employeeManageService.findAllEmployee(condition.getPredicate(), user.getUserId(), pageable);
        return null;
    }

}
