package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    IProductService productService;
    @GetMapping("/product")
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        try {
            List<Product> products = productService.findAll();
            modelAndView.addObject("products",products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @GetMapping("/product/create")
    public ModelAndView createNewForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("message","");
        modelAndView.addObject("product",new Product());
        return modelAndView;
    }
    @PostMapping("/product/save")
    public ModelAndView save(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        try {
            productService.create(product);
            modelAndView.addObject("product",new Product());
            modelAndView.addObject("message","Tao moi thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @GetMapping("/product/{id}/edit")
    public ModelAndView editForm(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        try {
            Product product = productService.findByID(id);
            modelAndView.addObject("message","");
            modelAndView.addObject("product",product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @PostMapping("/product/update")
    public ModelAndView edit(@ModelAttribute Product product){
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        try {
            productService.update(product.getId(), product);
            modelAndView.addObject("product",product);
            modelAndView.addObject("message","cap nhap thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @GetMapping("/product/{id}/view")
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/product/view");
        try {
            Product product = productService.findByID(id);
            modelAndView.addObject("product",product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    @GetMapping("/product/{id}/delete")
    public ModelAndView delete(@PathVariable int id){
        try {
            productService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showList();
    }
    @PostMapping("/product/search")
    public ModelAndView search(@RequestParam String name){
        if (name.equals("")){
            return showList();
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        try {
            Product product = productService.findByName(name);
            List<Product> products = new ArrayList<>();
            if(product!=null){
                products.add(product);
            }
            modelAndView.addObject("products",products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
