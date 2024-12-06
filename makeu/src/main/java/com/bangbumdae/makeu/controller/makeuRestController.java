package com.bangbumdae.makeu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.portpolioService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@RestController
public class makeuRestController {
    // private final memberService memberService;
    private final portpolioService pService;
    @GetMapping("/main/list")
    public List<ShopPortfolio> getPortpolios() {
        return pService.getPortfolios();
    }
}
