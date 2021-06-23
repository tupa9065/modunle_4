package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
    void setProvinceToNull (Province province);
    Page<Customer> findAll(Pageable pageable) throws Exception;
    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable) throws Exception;
    Optional<Customer> findOne(Long id) throws Exception;

}

