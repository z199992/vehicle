package com.xunlekj.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BaseException extends Exception{
    @Serial
    private static final long serialVersionUID = -2800863585054093446L;

    private final int httpStatus;

    public BaseException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
