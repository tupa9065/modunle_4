package com.codeGym.controller;

import com.codeGym.model.Customer;
import com.codeGym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> findAllCustomer(Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if(!customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        return new ResponseEntity<>(customerService.save(customer),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.delete(customerOptional.get());
        return new ResponseEntity<>(customerOptional.get(),HttpStatus.NO_CONTENT);
    }

}
