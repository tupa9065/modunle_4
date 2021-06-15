package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
        return customerRepository.updateCustomer(id,customer);
    }

    @Override
    public void deleteCustomer(int id) {
         customerRepository.deleteCustomer(id);
    }
}
