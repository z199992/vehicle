package com.xunlekj.system.user.repository;

import com.querydsl.core.types.Predicate;
import com.xunlekj.system.user.model.dto.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserInfoDetailRepository {
    Page<UserDetail> findAllUserDetail(Predicate predicate, Pageable pageable);

    Optional<UserDetail> findUserDetail(Predicate predicate);
}
