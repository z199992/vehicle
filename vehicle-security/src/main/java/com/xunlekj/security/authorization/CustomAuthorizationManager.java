package com.xunlekj.security.authorization;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.security.anotation.CheckModule;
import com.xunlekj.security.verification.ModuleVerification;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<MethodInvocation> {
    @Autowired
    private ModuleVerification moduleVerification;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, MethodInvocation methodInvocation) {
        CheckModule checkModule = AnnotationUtils.findAnnotation(methodInvocation.getMethod(), CheckModule.class);
        if(checkModule != null) {
            Authentication authentication = authenticationSupplier.get();
            if(authentication != null) {
                if(moduleVerification.mv((User) authentication.getPrincipal(), checkModule.module(), checkModule.operation())) {
                    return new AuthorizationDecision(true);
                } else {
                    return new AuthorizationDecision(false);
                }
            } else {
                return new AuthorizationDecision(false);
            }
        }

        return new AuthorizationDecision(true);
    }
}
