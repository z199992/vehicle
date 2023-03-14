package com.xunlekj.security.model.dto;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.user.model.entity.UserInfo;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class UserImpl implements User, Serializable {
    @Serial
    private static final long serialVersionUID = -7007231703681365183L;

    @Getter
    private final UserInfo userInfo;

    @Getter
    private final List<String> roles;

    private final Map<Module, OperationType> moduleOperationTypeMap;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
