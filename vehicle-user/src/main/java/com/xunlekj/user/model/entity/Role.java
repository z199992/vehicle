package com.xunlekj.user.model.entity;

import com.xunlekj.user.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.awt.event.AdjustmentListener;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AdjustmentListener.class)
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 6234097255858519933L;

    @Id
    private String id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
