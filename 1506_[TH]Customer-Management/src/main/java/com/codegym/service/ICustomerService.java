package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(int id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(int id, Customer customer);
    void deleteCustomer(int id);
}
