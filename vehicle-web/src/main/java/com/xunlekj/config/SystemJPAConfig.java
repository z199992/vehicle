package com.xunlekj.config;

import com.xunlekj.jpa.naming.CustomNamingStrategy;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.xunlekj.system.**", entityManagerFactoryRef = "systemEntityManagerFactory", transactionManagerRef = "systemTransactionManager")
public class SystemJPAConfig {
    @Bean("systemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean systemEntityManagerFactory(@Qualifier("systemDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.physical_naming_strategy", CustomNamingStrategy.class.getName());

        return builder.dataSource(dataSource)
                .packages("com.xunlekj.system.**", "com.xunlekj.system.user.model.entity")
                .persistenceUnit("system")
                .properties(properties)
                .build();
    }

    @Bean("systemTransactionManager")
    public PlatformTransactionManager systemTransactionManager(@Qualifier("systemEntityManagerFactory") EntityManagerFactory systemEntityManagerFactory) {
        return new JpaTransactionManager(systemEntityManagerFactory);
    }
}
