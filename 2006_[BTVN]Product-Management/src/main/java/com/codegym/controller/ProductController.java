package com.codegym.controller;

import com.codegym.model.category.Category;
import com.codegym.model.category.CategoryDAO;
import com.codegym.model.product.Product;
import com.codegym.model.product.ProductDAO;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Value("${upload_file}")
    private String filePath;

    @GetMapping
    public ModelAndView showListForm(){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    @GetMapping("/showCreateForm")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productDAO",new ProductDAO());
        modelAndView.addObject("categorise",categoryService.findAll());
        modelAndView.addObject("message",null);
        return modelAndView;
    }
    @PostMapping("/createNew")
    public ModelAndView createNew(@ModelAttribute ProductDAO productDAO){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        MultipartFile multipartFile = productDAO.getAvatar();
        String filename =multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(productDAO.getAvatar().getBytes(),new File(filePath+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Category category = categoryService.findById(productDAO.getCategory_id());
        Product product = new Product(productDAO.getName(),productDAO.getPrice(),productDAO.getDescription(),filename,category);
        productService.save(product);
        modelAndView.addObject("categoryDAO",new CategoryDAO());
        modelAndView.addObject("categorise",categoryService.findAll());
        modelAndView.addObject("message","successful new creation");

        return modelAndView;
    }
}
