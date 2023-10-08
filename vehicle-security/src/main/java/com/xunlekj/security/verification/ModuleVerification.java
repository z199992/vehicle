package com.xunlekj.security.verification;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import org.springframework.stereotype.Component;

@Component("MV")
public class ModuleVerification {

    /**
     * 判断用户使用有当前模块的操作权限
     *
     * @param user 用户
     * @param module 模块名称
     * @param operationType 操作类型
     * @return 是否有权限
     */
    public boolean mv(User user, Module module, OperationType operationType) {
        if(user.getModuleOperation().containsKey(module)) {
            return getOptionLevel(user.getModuleOperation().get(module)) >= getOptionLevel(operationType);
        }
        return false;
    }

    private int getOptionLevel(OperationType operationType) {
        return switch (operationType) {
            case View -> 1;
            case Add -> 10;
            case Update -> 20;
            case Delete -> 30;
            case Manager -> 50;
        };
    }

    public boolean isTenantUser(User user) {
        return user.getTenantId() != null;
    }
}
