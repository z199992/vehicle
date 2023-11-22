package com.xunlekj.module.system.tenant.controller;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.global.annotations.IsSystemAdministrator;
import com.xunlekj.module.system.tenant.model.condition.QueryTenantCondition;
import com.xunlekj.module.system.tenant.model.value.TenantAddValue;
import com.xunlekj.module.system.tenant.model.value.TenantUpdateValue;
import com.xunlekj.module.system.tenant.model.value.TenantValue;
import com.xunlekj.module.system.tenant.service.TenantManagerService;
import com.xunlekj.module.system.tenant.model.mapper.TenantMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
@Slf4j
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
    public Page<TenantValue> list(@ParameterObject QueryTenantCondition condition, Pageable pageable) {
        return service.findBy(condition.getPredicate(), pageable).map(mapper::toView);
    }

    @Operation(
            summary = "根据关键字获取租户",
            description = "获取租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @GetMapping("/list/keys")
    @IsSystemAdministrator
    public Page<TenantValue> listByKeys(@RequestParam List<String> keys, Pageable pageable) {
        return service.findByKeys(pageable, keys).map(mapper::toView);
    }

    @Operation(
            summary = "根据租户名称取租户",
            description = "获取租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @GetMapping("/list/name")
    @IsSystemAdministrator
    public List<TenantValue> listByName(@RequestParam String name) {
        return service.findAllByName(name).stream().map(mapper::toView).toList();
    }


    @Operation(
            summary = "新增租户",
            description = "新增租户, 仅系统管理员可用",
            security = @SecurityRequirement(name = "jwt"),
            tags = "Customer"
    )
    @PostMapping("/add")
    @IsSystemAdministrator
    public boolean add(@AuthenticationPrincipal User user, @Valid @RequestBody TenantAddValue value) throws LiquibaseException {
        service.addTenant(mapper.toEntity(value));
        log.info("add tenant success, name: {}, operator: {}", value.getName(), user.getUserId());
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
    public boolean update(@AuthenticationPrincipal User user, @Valid @RequestBody TenantUpdateValue value) {
        service.updateTenant(mapper.toEntity(value));
        log.info("update tenant success, name: {}, operator: {}", value.getName(), user.getUserId());
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
    public boolean disable(@AuthenticationPrincipal User user, @PathVariable String id) {
        service.disableTenant(id);
        log.info("disable tenant success, id: {}, operator: {}", id, user.getUserId());
        return true;
    }
}
