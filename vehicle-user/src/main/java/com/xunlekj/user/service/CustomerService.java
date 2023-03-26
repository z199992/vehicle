package com.xunlekj.user.service;

import com.xunlekj.user.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer save(Customer customer);

    void remove(String id);
}
