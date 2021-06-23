package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepository;
import com.codegym.service.provice.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("provinces")
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;
    @Autowired
    private ICustomerRepository customerService;

    @GetMapping
    public ModelAndView showListForm(){
        ModelAndView modelAndView = new ModelAndView("/province/list");
        try {
            modelAndView.addObject("provinces",provinceService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm (){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province",new Province());
        modelAndView.addObject("message",null);
        return modelAndView;

    }
    @PostMapping("/save")
    public ModelAndView createNew (@ModelAttribute Province province){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        provinceService.save(province);
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("message", "Tao moi thanh cong");
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public  ModelAndView viewInfo(@PathVariable Long id){
        Optional<Province> province = provinceService.findById(id);
        if(!province.isPresent()){
            return new ModelAndView("/error.404");
        }
        Iterable<Customer> customers = customerService.findAllByProvince(province.get());
        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province",province.get());
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public  ModelAndView delete(@ModelAttribute Province province){
        customerService.setProvinceToNull(province);
        provinceService.remove(province.getId());
        ModelAndView modelAndView = new ModelAndView("/province/list");
        try {
            modelAndView.addObject("provinces",provinceService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public  ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province",provinceService.findById(id));
        modelAndView.addObject("message", null);

        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit (@ModelAttribute Province province){
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        provinceService.save(province);
        modelAndView.addObject("province",province);
        modelAndView.addObject("message", "chinh sua thanh cong");
        return modelAndView;
    }

}
