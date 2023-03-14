package com.xunlekj.user.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.awt.event.AdjustmentListener;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(RoleMappingId.class)
@EntityListeners(AdjustmentListener.class)
public class RoleMapping implements Serializable {
    @Serial
    private static final long serialVersionUID = 8588152109483746082L;

    @Id
    private String userId;
    @Id
    private String roleId;

    private String customerId;

    private String shopId;

    @CreatedDate
    private LocalDateTime createTime;
}
