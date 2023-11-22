package com.xunlekj.exception;

import java.io.Serial;

/**
 * 登录异常
 */
public class LoginException extends BaseException {
    @Serial
    private static final long serialVersionUID = -4245082226908377609L;

    public LoginException(String message) {
        super(400, message);
    }
}
