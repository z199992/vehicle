package com.xunlekj.exception;

import java.io.Serial;

public class NotFoundDataException extends BaseException{
    @Serial
    private static final long serialVersionUID = 3540532330256815987L;

    public NotFoundDataException(String message) {
        super(400, message);
    }
}
