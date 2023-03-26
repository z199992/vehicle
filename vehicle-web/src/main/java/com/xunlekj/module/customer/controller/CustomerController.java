package com.xunlekj.module.customer.controller;

import com.xunlekj.module.customer.annotations.IsSystemAdministrator;
import com.xunlekj.module.customer.model.mapper.CustomerMapper;
import com.xunlekj.module.customer.model.value.CustomerAddValue;
import com.xunlekj.module.customer.model.value.CustomerUpdateValue;
import com.xunlekj.user.service.CustomerService;
import com.xunlekj.user.model.entity.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerMapper mapper;

    @Operation(
            summary = "获取全部租户",
            description = "获取全部租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @GetMapping("/list")
    @IsSystemAdministrator
    public Page<Customer> list(Pageable pageable) {
        return service.findAll(pageable);
    }


    @Operation(
            summary = "新增租户",
            description = "新增租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @PostMapping("/add")
    @IsSystemAdministrator
    public boolean add(@Valid @RequestBody CustomerAddValue value) {
        service.save(mapper.toEntity(value));
        return true;
    }

    @Operation(
            summary = "修改租户",
            description = "修改租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @PostMapping("/update")
    @IsSystemAdministrator
    public boolean update(@Valid @RequestBody CustomerUpdateValue value) {
        service.save(mapper.toEntity(value));
        return true;
    }

    @Operation(
            summary = "删除租户",
            description = "删除租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @PostMapping("/remove/{id}")
    @IsSystemAdministrator
    public boolean update(@PathVariable String id) {
        service.remove(id);
        return true;
    }
}
