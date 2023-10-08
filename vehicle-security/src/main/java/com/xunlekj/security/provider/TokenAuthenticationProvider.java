package com.xunlekj.security.provider;

import com.xunlekj.security.service.JsonWebTokenService;
import com.xunlekj.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JsonWebTokenService jsonWebTokenService;
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken bearer = (BearerTokenAuthenticationToken) authentication;
        try {
            String account = jsonWebTokenService.getAccountByToken(bearer.getToken());
            UserDetails user = userService.loadUserByUsername(account);
            if(user != null) {
                userService.checkUserCanLogin(user);
                return new UsernamePasswordAuthenticationToken(user, bearer.getToken(), user.getAuthorities());
            } else {
                throw new InvalidBearerTokenException("Failed to authenticate since the JWT was invalid");
            }
        } catch (Exception e) {
            throw new InvalidBearerTokenException("Failed to authenticate since the JWT was invalid", e);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
