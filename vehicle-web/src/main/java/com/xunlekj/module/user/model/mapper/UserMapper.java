package com.xunlekj.module.user.model.mapper;

import com.xunlekj.auth.model.dto.User;
import com.xunlekj.module.user.model.value.AddUserValue;
import com.xunlekj.module.user.model.view.CurrentUserInfo;
import com.xunlekj.module.user.model.view.UserView;
import com.xunlekj.system.user.model.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy/MM/dd HH:mm:ss")
    UserView toView(UserInfo info);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "name", source = "nickName")
    CurrentUserInfo toCurrentUserInfo(User user);

    UserInfo toEntity(AddUserValue value);
}
