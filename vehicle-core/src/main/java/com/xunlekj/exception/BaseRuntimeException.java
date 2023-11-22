package com.xunlekj.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BaseRuntimeException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -184694851359727249L;

    private final int httpStatus;

    public BaseRuntimeException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
