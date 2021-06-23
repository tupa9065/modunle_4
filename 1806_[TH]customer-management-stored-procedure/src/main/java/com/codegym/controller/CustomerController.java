package com.codegym.controller;

import com.codegym.exception.DuplicateEmailException;
import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.IGeneralService;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.provice.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        try {
            return provinceService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/customer/inputs-not-acceptable");
    }
    @GetMapping
    public ModelAndView showList(@RequestParam("search") Optional<String> search, @PageableDefault(size = 5) Pageable pageable){
        Page<Customer> customers = null;
        if(search.isPresent()){
            try {
                customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                customers = customerService.findAll(pageable);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public ModelAndView createNew(@ModelAttribute Customer customer) throws DuplicateEmailException {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("/customer/create");
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("message", "tao moi thanh cong");
            modelAndView.addObject("green","green");
            return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView showViewForm(@PathVariable Long id){
        try {
            ModelAndView modelAndView = new ModelAndView("/customer/view");
            Optional<Customer> customerOptional = customerService.findOne(id);
            modelAndView.addObject("customer",customerOptional.get());
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customerService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Customer customer) throws DuplicateEmailException {
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
