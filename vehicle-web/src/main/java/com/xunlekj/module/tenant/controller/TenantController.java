package com.xunlekj.module.tenant.controller;

import com.xunlekj.global.annotations.IsSystemAdministrator;
import com.xunlekj.module.tenant.model.mapper.TenantMapper;
import com.xunlekj.module.tenant.model.value.TenantAddValue;
import com.xunlekj.module.tenant.model.value.TenantUpdateValue;
import com.xunlekj.module.tenant.service.TenantManagerService;
import com.xunlekj.system.tenant.model.Tenant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import liquibase.exception.LiquibaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    @Autowired
    private TenantManagerService service;
    @Autowired
    private TenantMapper mapper;

    @Operation(
            summary = "获取全部租户",
            description = "获取全部租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @GetMapping("/list")
    @IsSystemAdministrator
    public Page<Tenant> list(Pageable pageable) {
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
    public boolean add(@Valid @RequestBody TenantAddValue value) throws LiquibaseException {
        service.addTenant(mapper.toEntity(value));
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
    public boolean update(@Valid @RequestBody TenantUpdateValue value) {
        service.updateTenant(mapper.toEntity(value));
        return true;
    }

    @Operation(
            summary = "禁用租户",
            description = "禁用租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @PostMapping("/disable/{id}")
    @IsSystemAdministrator
    public boolean disable(@PathVariable String id) {
        service.disableTenant(id);
        return true;
    }
}
