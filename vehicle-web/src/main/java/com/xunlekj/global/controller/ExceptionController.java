package com.xunlekj.global.controller;

import com.xunlekj.exception.BaseException;
import com.xunlekj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ObjectError>> bindException(BindException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exception.getAllErrors());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<String> authenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("您没有登录, 请进行登录!");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(AuthenticationCredentialsNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> baseException(BaseException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
    }

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<String> baseRuntimeException(BaseRuntimeException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
    }
}
