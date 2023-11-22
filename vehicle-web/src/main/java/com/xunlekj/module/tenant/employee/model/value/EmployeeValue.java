package com.xunlekj.module.tenant.employee.model.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeValue {
    @Schema(description = "工号")
    private String jobNumber;
    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名")
    private String name;
    @NotBlank(message = "性别不能为空")
    @Schema(description = "性别")
    private String gender;
    @Schema(description = "手机号码")
    private String phone;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "地址")
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "入职时间")
    private LocalDate entryTime;
    @Schema(description = "底薪")
    private BigDecimal baseSalary;
    @Schema(description = "身份证号码")
    private String idCard;
    @Schema(description = "备注")
    private String remark;
}
