package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.IGeneralService;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.provice.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    public ICustomerService customerService ;
    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }
    @GetMapping
    public ModelAndView showList(@RequestParam("search") Optional<String> search,Pageable pageable){
        Page<Customer> customers;
        if(search.isPresent()){
            customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
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
        customerService.save(customer);
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "tao moi thanh cong");
        modelAndView.addObject("green","green");
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customerService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        customerService.save(customer);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            customerService.remove(id);
        }
        else {
            return "/error.404";
        }
        return "redirect:/customers";
    }

}
