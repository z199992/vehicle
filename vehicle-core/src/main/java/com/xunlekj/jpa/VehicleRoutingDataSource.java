package com.xunlekj.jpa;

import com.xunlekj.auth.model.dto.User;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class VehicleRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).getTenantId();
        }
        return TenantContext.getCurrentTenant();
    }

    public void addTargetDataSource(String tenantId, DataSource dataSource) {
        Map<Object, Object> dynamicTargetDataSources = new HashMap<>(getResolvedDataSources());
        dynamicTargetDataSources.put(tenantId, dataSource);
        setTargetDataSources(dynamicTargetDataSources);
        afterPropertiesSet();
    }
}
