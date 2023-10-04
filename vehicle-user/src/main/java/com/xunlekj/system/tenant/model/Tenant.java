package com.xunlekj.system.tenant.model;

import com.xunlekj.jpa.annotations.CustomTableGenerator;
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
public class Tenant implements Serializable {
    @Serial
    private static final long serialVersionUID = 7760197950496139482L;

    @Id
    @CustomTableGenerator(type = "TEN")
    private String id;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime expireTime;

    private Boolean locked;

    private Boolean enable;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
