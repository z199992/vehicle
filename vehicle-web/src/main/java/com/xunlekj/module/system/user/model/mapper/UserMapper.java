package com.xunlekj.module.system.user.model.mapper;

import com.xunlekj.enums.Module;
import com.xunlekj.enums.OperationType;
import com.xunlekj.module.system.user.model.value.AddSuperUserValue;
import com.xunlekj.module.system.user.model.value.AddTenantManager;
import com.xunlekj.module.system.user.model.value.UpdateUserValue;
import com.xunlekj.module.system.user.model.view.CurrentUserInfo;
import com.xunlekj.module.system.user.model.view.UserView;
import com.xunlekj.module.tenant.employee.model.value.AddUserValue;
import com.xunlekj.system.user.model.dto.UserDetail;
import com.xunlekj.system.user.model.entity.UserInfo;
import com.xunlekj.tenant.employee.model.entity.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserView toView(UserInfo info);

    @Mapping(target = "tenantName", source = "tenant.name")
    UserView toView(UserDetail info);

    @Mapping(target = "id", ignore = true)
    UserInfo toEntity(AddSuperUserValue value);

    @Mapping(target = "id", source = "userInfo.id")
    @Mapping(target = "name", source = "userInfo.nickName")
    CurrentUserInfo toCurrentUserInfo(UserInfo userInfo, Employee employee, List<String> roles, Map<Module, OperationType> moduleOperation);

    @Mapping(target = "id", ignore = true)
    UserInfo toEntity(AddTenantManager value);

    @Mapping(target = "nickName")
    @Mapping(target = "phone")
    @Mapping(target = "email")
    @Mapping(target = "enable")
    @BeanMapping(ignoreByDefault = true)
    UserInfo updateBaseInfo(@MappingTarget UserInfo target, UserInfo source);

    UserInfo toEntity(UpdateUserValue userValue);

    UserInfo toEntity(AddUserValue user);
}
