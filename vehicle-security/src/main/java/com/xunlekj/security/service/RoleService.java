package com.xunlekj.security.service;

import java.util.List;

public interface RoleService {
    List<String> getRolesByUser(String userId);
}
