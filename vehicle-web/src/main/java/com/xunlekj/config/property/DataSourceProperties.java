package com.xunlekj.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("vehicle.datasource")
@Data
public class DataSourceProperties {
    private String driverClassName;
    private String address;
    private String url;
    private String username;
    private String password;
    private String systemChangeLog;
    private String tenantChangeLog;
}
