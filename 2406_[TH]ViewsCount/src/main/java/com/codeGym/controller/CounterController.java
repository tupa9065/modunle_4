package com.codeGym.controller;

import com.codeGym.model.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("counter")
public class CounterController {
    @ModelAttribute("counter")
    public Counter setupCounter(){
        return new Counter();
    }
    @GetMapping("/")
    public ModelAndView showList(@ModelAttribute("counter") Counter counter){
        ModelAndView modelAndView = new ModelAndView("/index");
        counter.increment();
        return modelAndView;
    }
}
