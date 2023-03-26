package com.xunlekj.module.customer.model.mapper;

import com.xunlekj.module.customer.model.value.CustomerAddValue;
import com.xunlekj.module.customer.model.value.CustomerUpdateValue;
import com.xunlekj.user.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerAddValue value);

    Customer toEntity(CustomerUpdateValue value);
}
