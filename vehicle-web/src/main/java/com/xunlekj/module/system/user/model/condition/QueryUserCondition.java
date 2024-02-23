package com.xunlekj.module.system.user.model.condition;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.xunlekj.model.condition.Condition;
import com.xunlekj.system.user.model.entity.QUserInfo;
import com.xunlekj.system.user.model.entity.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QueryUserCondition implements Condition {
    @Schema(description = "账号")
    private String account;
    @Schema(description = "昵称")
    private String name;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "用户类型")
    private UserType userType;
    @Schema(description = "是否启用")
    private Boolean enabled;
    @Schema(description = "是否锁定")
    private Boolean isLocked;
    @Schema(description = "租户ID")
    private String tenantId;


    @Override
    public Predicate getPredicate() {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(createLikePredicate(account, QUserInfo.userInfo.account));
        builder.and(createLikePredicate(name, QUserInfo.userInfo.nickName));
        builder.and(createLikePredicate(email, QUserInfo.userInfo.email));
        builder.and(createLikePredicate(phone, QUserInfo.userInfo.phone));
        builder.and(createPredicate(userType, QUserInfo.userInfo.type::eq));
        builder.and(createPredicate(enabled, QUserInfo.userInfo.enable::eq));
        builder.and(createPredicate(isLocked, QUserInfo.userInfo.locked::eq));
        builder.and(createPredicate(tenantId, QUserInfo.userInfo.tenantId::eq));

        return builder;
    }
}
