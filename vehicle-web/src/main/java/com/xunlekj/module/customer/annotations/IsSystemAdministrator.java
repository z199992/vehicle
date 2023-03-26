package com.xunlekj.module.customer.annotations;

import com.xunlekj.user.constant.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('" + RoleConstants.Administrator + "')")
public @interface IsSystemAdministrator {
}
