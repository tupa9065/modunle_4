package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(){
        return "index";
    }
    @GetMapping("/result")
    public String result(@RequestParam int num1,@RequestParam int num2, Model model){
        model.addAttribute("num1",num1);
        model.addAttribute("num2",num2);
        return "result";
    }
}
