package com.xunlekj.jpa.annotations;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType( com.xunlekj.jpa.enhanced.CustomTableGenerator.class )
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface CustomTableGenerator {
    int length() default 17;

    String type();

    String prefix() default "";
}
