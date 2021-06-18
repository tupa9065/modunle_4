package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    public ICustomerService customerService;
    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("customer/list");
        //List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", null);
        return modelAndView;
    }
    @PostMapping("/save")
    public ModelAndView saveNew(@ModelAttribute(name = "customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        customerService.save(customer);
        modelAndView.addObject("message", "tao moi thanh cong");
        return modelAndView;
    }
    @GetMapping("{id}/edit")
    public ModelAndView edit(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute(name = "customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        customerService.save(customer);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable(name = "id") Long id){

        customerService.remove(id);

        return showList();
    }

}
