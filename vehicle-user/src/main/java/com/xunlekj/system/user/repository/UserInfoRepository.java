package com.xunlekj.system.user.repository;

import com.xunlekj.system.user.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String>, QuerydslPredicateExecutor<UserInfo>, UserInfoDetailRepository {

}
