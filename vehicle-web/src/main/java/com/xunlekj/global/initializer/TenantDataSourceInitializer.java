package com.xunlekj.global.initializer;

import com.xunlekj.module.tenant.service.TenantManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TenantDataSourceInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TenantManagerService tenantManagerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Load all tenant data source");
        tenantManagerService.loadAllTenantDataSource();
    }
}
