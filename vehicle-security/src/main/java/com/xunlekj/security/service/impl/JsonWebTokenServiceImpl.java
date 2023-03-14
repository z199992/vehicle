package com.xunlekj.security.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import com.xunlekj.security.service.JsonWebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JsonWebTokenServiceImpl implements JsonWebTokenService {
    @Autowired
    private RSAKey rsaKey;
    @Autowired
    private JWSSigner jwsSigner;

    @Autowired
    private JWSVerifier jwsVerifier;

    @Value("${security.token.subject}")
    private String tokenSubject;
    @Value("${security.token.issuer}")
    private String tokenIssuer;

    @Override
    public String createToken(String username) throws Exception {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(tokenSubject)
                .issuer(tokenIssuer)
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .claim("ACCOUNT",username)
                .build();

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).keyID(rsaKey.getKeyID()).build();
        JWSObject jwt = new SignedJWT(header, claimsSet);
        jwt.sign(jwsSigner);
        return jwt.serialize();
    }

    @Override
    public String getAccountByToken(String token) throws Exception {
        return getAccountOfToken(token);
    }

    private String getAccountOfToken(String token) throws Exception  {
        SignedJWT signedJWT = SignedJWT.parse(token);
        if(!signedJWT.verify(jwsVerifier)) {
            throw new JwtException("Token signature is illegal!");
        }
        if(DateUtils.isAfter(new Date(), signedJWT.getJWTClaimsSet().getExpirationTime(), 600)) {
            throw new JwtException("Token expired!");
        }

        return signedJWT.getJWTClaimsSet().getClaim("ACCOUNT").toString();
    }
}
