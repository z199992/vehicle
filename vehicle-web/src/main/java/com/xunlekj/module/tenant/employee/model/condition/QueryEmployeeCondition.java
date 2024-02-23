package com.xunlekj.module.tenant.employee.model.condition;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.xunlekj.model.condition.Condition;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data()
@Accessors(chain = true)
public class QueryEmployeeCondition implements Condition {
    @Getter(AccessLevel.PRIVATE)
    private final BooleanBuilder builder;

    private String name;

    public QueryEmployeeCondition() {
        builder = new BooleanBuilder();
    }

    @Override
    public Predicate getPredicate() {
        return builder;
    }
}
