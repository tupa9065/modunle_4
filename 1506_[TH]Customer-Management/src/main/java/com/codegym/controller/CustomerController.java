package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customer/list")
    public ModelAndView showList() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("message","");

        return modelAndView;
    }

    @GetMapping("/customer/create")
    public ModelAndView addNewForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/customer/save")
    public ModelAndView saveNew(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        customerService.createCustomer(customer);
        modelAndView.addObject("message","tao thanh cong");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @GetMapping("/customer/{id}/edit")
    public  ModelAndView editForm(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","");
        return modelAndView;
    }
    @PostMapping("/customer/update")
    public ModelAndView update(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        customerService.updateCustomer(customer.getId(),customer);
        modelAndView.addObject("message","sua thanh cong");
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
    @GetMapping("/customer/{id}/delete")
    public  ModelAndView delete(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        customerService.deleteCustomer(id);
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers",customers);
        modelAndView.addObject("message","xoa thanh cong");
        return modelAndView;
    }
    @GetMapping("/customer/{id}/view")
    public  ModelAndView viewForm(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
}
