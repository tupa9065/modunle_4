package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository{
    private static Map<Integer,Customer> customers = new HashMap<>();
    static {
        customers.put(1, new Customer(1, "John", "john@codegym.vn", "Hanoi"));
        Customer.lastId++;
        customers.put(2, new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        Customer.lastId++;
        customers.put(3, new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
        Customer.lastId++;
        customers.put(4, new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        Customer.lastId++;
        customers.put(5, new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        Customer.lastId++;
        customers.put(6, new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
        Customer.lastId++;
    }
    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer findById(int id) {
        return customers.get(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setId(Customer.lastId++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
        customers.replace(id,customer);
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        customers.remove(id);
    }
}
