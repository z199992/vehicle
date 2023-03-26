package com.xunlekj.security.model.dto;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.user.model.entity.UserInfo;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserImpl implements User, Serializable {
    @Serial
    private static final long serialVersionUID = -7007231703681365183L;

    @Getter
    private final UserInfo userInfo;

    private final Map<Module, OperationType> moduleOperationTypeMap;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserImpl(UserInfo userInfo, Map<Module, OperationType> moduleOperationTypeMap, List<String> roles) {
        this.userInfo = userInfo;
        this.moduleOperationTypeMap = moduleOperationTypeMap;
        this.authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Map<Module, OperationType> getModuleOperation() {
        return moduleOperationTypeMap;
    }

    @Override
    public String getUserId() {
        return getUserInfo().getId();
    }

    @Override
    public String getNickName() {
        return getUserInfo().getNickName();
    }

    @Override
    public List<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
