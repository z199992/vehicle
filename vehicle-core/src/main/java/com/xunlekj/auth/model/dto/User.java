package com.xunlekj.auth.model.dto;

import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface User extends UserDetails {
    Map<Module, OperationType> getModuleOperation();

    String getUserId();

    String getNickName();

    List<String> getRoles();

    String getTenantId();
}
