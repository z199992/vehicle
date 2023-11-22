package com.xunlekj.model.condition;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Condition {
    Predicate getPredicate();

    default <T> Predicate createPredicate(T field, Function<T, Predicate> function) {
        return Optional.ofNullable(field).map(function).orElse(null);
    }

    default <T> Predicate createLikePredicate(String field, StringPath path) {
        return createPredicate(Optional.ofNullable(field).map(f -> f.isBlank() ? null : "%" + f.trim() + "%").orElse(null), path::likeIgnoreCase);
    }

    default <T> Predicate createPredicate(T field, Supplier<Predicate> supplier) {
        return Optional.ofNullable(field).map(f -> supplier.get()).orElse(null);
    }
}
