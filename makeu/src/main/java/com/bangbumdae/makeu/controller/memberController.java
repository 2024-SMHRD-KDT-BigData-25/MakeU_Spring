package com.bangbumdae.makeu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.repository.memberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class memberController {
    private final memberRepository memberRepository;
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
    
    // 회원가입
    @PostMapping("/members")
    public String addMember(Members m) {
        memberRepository.save(m);
        return "index";
    }

}
