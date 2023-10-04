package com.xunlekj.module.tenant.model.mapper;

import com.xunlekj.module.tenant.model.value.TenantAddValue;
import com.xunlekj.module.tenant.model.value.TenantUpdateValue;
import com.xunlekj.system.tenant.model.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    Tenant toEntity(TenantAddValue value);

    Tenant toEntity(TenantUpdateValue value);
}
