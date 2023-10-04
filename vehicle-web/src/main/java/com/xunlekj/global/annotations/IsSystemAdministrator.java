package com.xunlekj.global.annotations;

import com.xunlekj.constant.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('" + RoleConstants.Administrator + "', '" + RoleConstants.SystemSuperUser + "')")
public @interface IsSystemAdministrator {
}
