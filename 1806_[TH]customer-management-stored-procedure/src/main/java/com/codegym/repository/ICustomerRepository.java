package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);

    @Transactional
    @Modifying
    @Query("update Customer as c set c.province=null where c.province=:province" )
    void setProvinceToNull (@Param("province") Province province);

    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);

}
