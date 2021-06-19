package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    public ICustomerService customerService ;
    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", null);
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView createNew(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        customerService.insertWithStoredProcedure(customer);
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "tao moi thanh cong");
        modelAndView.addObject("green","green");
        return modelAndView;
    }

}
