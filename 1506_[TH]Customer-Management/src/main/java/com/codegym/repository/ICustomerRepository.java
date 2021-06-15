package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(int id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);
}
