package com.xunlekj.module.system.user.model.view;

import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CurrentUserInfo {
    private String id;
    @Schema(description = "用户昵称")
    private String name;
    @Schema(description = "用户角色")
    private List<String> roles;
    @Schema(description = "用户模块操作设定")
    private Map<Module, OperationType> moduleOperation;
}
