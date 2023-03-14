package com.xunlekj.security.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.xunlekj.security.provider.TokenAuthenticationProvider;
import com.xunlekj.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, BearerTokenAuthenticationFilter tokenFilter) throws Exception {
        http.cors();
        http.csrf().disable().addFilter(tokenFilter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.anonymous().disable();
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserService userService, TokenAuthenticationProvider tokenAuthenticationProvider) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userService);
        auth.authenticationProvider(tokenAuthenticationProvider);
        auth.authenticationProvider(userProvider(userService));
        return auth.build();

    }

    @Bean
    public BearerTokenAuthenticationFilter tokenFilter(AuthenticationManager authenticationManager) throws Exception {
        BearerTokenAuthenticationFilter tokenFilter = new BearerTokenAuthenticationFilter(authenticationManager);
        tokenFilter.setBearerTokenResolver(bearerTokenResolver());

        return tokenFilter;
    }

    @Bean
    public BearerTokenResolver bearerTokenResolver() {
        return new DefaultBearerTokenResolver();
    }

    @Bean
    public DaoAuthenticationProvider userProvider(UserService userService) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public JWSSigner jwsSigner(RSAKey rsaKey) throws Exception {
        return new RSASSASigner(rsaKey);
    }

    @Bean
    public JWSVerifier jwsVerifier(RSAKey rsaKey) throws JOSEException {
        return new RSASSAVerifier(rsaKey.toPublicJWK());
    }

    @Bean
    public RSAKey rsaKey() throws Exception {
        return generateRsaKey();
    }
    private RSAKey generateRsaKey() throws JOSEException, IOException, CertificateException, ParseException {
        return RSAKey.parse(FileUtils.readFileToString(getRsaFile()));
    }

    private File getRsaFile() throws IOException, JOSEException {
        ClassPathResource resource = new ClassPathResource("/config/token.json");

        if(resource.exists()) {
            FileTime fileTime = (FileTime) Files.getAttribute(resource.getFile().toPath(), "creationTime");
            if(LocalDateTime.now().plusDays(-1).isBefore(LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault()))) {
                return resource.getFile();
            } else {
                if(resource.getFile().delete()) {
                    log.info("证书时间超过24小时已删除!");
                } else {
                    log.error("证书删除失败!");
                    return resource.getFile();
                }
            }
        }

        byte[] sharedKey = new byte[32];
        new SecureRandom().nextBytes(sharedKey);
        RSAKey rsaKey = new RSAKeyGenerator(2048)
                .keyID(new String(sharedKey))
                .keyID("xunlekj")
                .generate();
        File file = new File(resource.getClassLoader().getResource(".").getFile(), resource.getPath());
        FileUtils.touch(file);
//        file.createNewFile();
        FileUtils.writeStringToFile(file, rsaKey.toJSONString());
        return file;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
