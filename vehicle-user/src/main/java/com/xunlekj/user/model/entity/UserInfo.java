package com.xunlekj.user.model.entity;

import com.xunlekj.auth.model.dto.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String account;
    private String password;
    private String nickName;

    private LocalDateTime expireTime;
    private LocalDateTime credentialsExpireTime;
    private boolean locked;
    private boolean enable;

    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
}
