package com.xunlekj.config;

import com.xunlekj.jpa.VehicleRoutingDataSource;
import com.xunlekj.jpa.naming.CustomNamingStrategy;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.xunlekj.tenant.**", entityManagerFactoryRef = "TenantEntityManagerFactory", transactionManagerRef = "TenantTransactionManager")
public class TenantJpaConfig {
    @Bean("TenantEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(VehicleRoutingDataSource dataSource, EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", CustomNamingStrategy.class.getName());

        return builder.dataSource(dataSource)
                .packages("com.xunlekj.tenant.**")
                .persistenceUnit("tenant")
                .properties(properties)
                .build();
    }

    @Bean("TenantTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
