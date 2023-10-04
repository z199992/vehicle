package com.xunlekj.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "jwt", type= SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {
//    @Bean
//    public OpenAPI openAPI() {
//        OpenAPI openAPI = new OpenAPI();
//
//        return openAPI;
//    }

}
