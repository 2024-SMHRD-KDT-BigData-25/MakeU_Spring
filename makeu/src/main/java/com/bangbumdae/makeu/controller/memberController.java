package com.bangbumdae.makeu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bangbumdae.makeu.model.Members;

@Controller
public class memberController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/update")
    public String updatePage() {
        return "update";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }
    
    @PostMapping("/members")
    public String addMember(Members m) {
        System.out.println(m.toString());
        return "index";
    }

}
