package com.xunlekj.security.service;

public interface JsonWebTokenService {
    String createToken(String account) throws Exception;

    String getAccountByToken(String token) throws Exception;
}
