package com.xunlekj.module.system.tenant.model.condition;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.xunlekj.model.condition.Condition;
import com.xunlekj.system.tenant.model.QTenant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QueryTenantCondition implements Condition {
    @Schema(description = "租户名称")
    private String name;
    @Schema(description = "电话号码")
    private String phone;
    @Schema(description = "电子邮件")
    private String email;

    @Override
    public Predicate getPredicate() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(createLikePredicate(name, QTenant.tenant.name));
        booleanBuilder.and(createLikePredicate(phone, QTenant.tenant.phone));
        booleanBuilder.and(createLikePredicate(email, QTenant.tenant.email));
        return booleanBuilder;
    }
}
