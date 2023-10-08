package com.xunlekj.security.anotation;

import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CheckModule {
    Module module();

    OperationType operation();
}
