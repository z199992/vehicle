package com.xunlekj.system.user.model.entity;

import com.xunlekj.jpa.annotations.CustomTableGenerator;
import com.xunlekj.system.user.model.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 3747799467500583519L;

    @Id
    @CustomTableGenerator(type = "USI")
    private String id;
    private String account;
    private String password;
    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private LocalDateTime expireTime;
    private LocalDateTime credentialsExpireTime;

    private Boolean locked = false;
    private Boolean enable = true;

    private String tenantId;

    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
}
