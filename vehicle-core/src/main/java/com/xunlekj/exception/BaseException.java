package com.xunlekj.exception;

import java.io.Serial;

public class BaseException extends Exception{
    @Serial
    private static final long serialVersionUID = -2800863585054093446L;

    private final int httpStatus;

    public BaseException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
