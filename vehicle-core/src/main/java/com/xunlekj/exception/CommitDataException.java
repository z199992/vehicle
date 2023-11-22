package com.xunlekj.exception;

import java.io.Serial;

/**
 * 提交数据异常
 */
public class CommitDataException extends BaseRuntimeException {
    @Serial
    private static final long serialVersionUID = -194499467645600623L;

    public CommitDataException(String message) {
        super(10001, message);
    }
}
