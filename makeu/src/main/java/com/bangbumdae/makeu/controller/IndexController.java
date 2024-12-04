package com.bangbumdae.makeu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
    
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }
}
