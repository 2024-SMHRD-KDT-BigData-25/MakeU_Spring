package com.bangbumdae.makeu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.service.memberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class memberController {
    @Autowired
    private final memberService memberService;
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
        memberService.addMember(m);
        return "index";
    }

    @PostMapping("/Login")
    public String memberLogin(Members m, HttpSession session) {
        System.out.println(m.toString());
    
        // Service를 통해 로그인 처리
        List<Members> result = memberService.authenticate(m.getMemId(), m.getMemPw());
        
        if (result.isEmpty()) {
            System.out.println("아이디와 비밀번호를 확인하세요!");
            session.setAttribute("error", "아이디와 비밀번호를 확인하세요!");
            return "login"; // 실패 시 로그인 페이지로 이동
        } else {
            System.out.println("로그인 성공!");
            session.setAttribute("members", result.get(0));
            return "redirect:/"; // 성공 시 메인 페이지로 이동
        }
    }

}
