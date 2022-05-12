package com.velcho.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping({"/","index"})
    public ModelAndView showHome(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

}
