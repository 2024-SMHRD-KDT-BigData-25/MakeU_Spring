package com.bangbumdae.makeu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.portpolioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final portpolioService pService;
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
    
    @GetMapping("/main")
    public String mainPage(Model model) {
        List<ShopPortfolio> list = pService.getPortfolios();
        model.addAttribute("portpolios", list);
        return "main";
    }
}
