package com.codegym.controller;

import com.codegym.model.category.Category;
import com.codegym.model.category.CategoryDAO;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService ;
    @GetMapping
    public ModelAndView showListForm(){
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categorise",categoryService.findAll());
        return modelAndView;
    }
    @GetMapping("/showCreateForm")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("categoryDAO",new CategoryDAO());
        modelAndView.addObject("message",null);
        return modelAndView;
    }
    @PostMapping("/createNew")
    public ModelAndView createNew(@ModelAttribute CategoryDAO categoryDAO){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        Category category = new Category(categoryDAO.getName());
            categoryService.save(category);
            modelAndView.addObject("categoryDAO",new CategoryDAO());
            modelAndView.addObject("message","successful new creation");

        return modelAndView;
    }

}
