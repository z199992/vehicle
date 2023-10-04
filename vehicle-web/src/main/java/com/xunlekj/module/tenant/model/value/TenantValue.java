package com.xunlekj.module.tenant.model.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class TenantValue {
    private String id;

    @Schema(description = "租户名称")
    @NotBlank(message = "租户名称不能为空")
    @Length(max = 30, message = "客户名称不能超过30个字")
    private String name;

    @Schema(description = "电话号码")
    @NotBlank(message = "租户电话不能为空")
    private String phone;

    @Schema(description = "电子邮件")
    @Email(message = "租户电子邮箱格式错误")
    private String email;

    @Schema(description = "有效时间")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate expireTime;

    @Schema(description = "是否锁定, 默认False")
    private Boolean locked = false;
    @Schema(description = "是否启用")
    private Boolean enable = true;
}
