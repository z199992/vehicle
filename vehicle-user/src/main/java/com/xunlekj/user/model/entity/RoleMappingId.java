package com.xunlekj.user.model.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RoleMappingId implements Serializable {
    @Serial
    private static final long serialVersionUID = -2562371064448809718L;

    private String userId;

    private String roleId;
}
