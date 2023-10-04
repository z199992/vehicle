package com.xunlekj.module.user.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@MV.mv(principal, T(com.xunlekj.enums.Module).SystemUser, T(com.xunlekj.enums.OperationType).View)")
public @interface CheckUserModelView {
}
