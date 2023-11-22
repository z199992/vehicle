package com.xunlekj.system.user.model.dto;

import com.xunlekj.system.tenant.model.Tenant;
import com.xunlekj.system.user.model.entity.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetail extends UserInfo {
    @Serial
    private static final long serialVersionUID = 6931728962577047486L;
    private Tenant tenant;
}
