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

        return true;
    }
}
