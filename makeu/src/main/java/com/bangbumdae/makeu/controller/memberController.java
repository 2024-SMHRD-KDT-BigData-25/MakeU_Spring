package com.bangbumdae.makeu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.memberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class memberController {
    private final memberService memberService;
    private final makeuplikesService makeuplikesService;
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

    // 마이페이지
    @GetMapping("/mypage")
    public String showMyPage(@RequestParam("memid") String memid, Model model) {
        // memId 파라미터 값 출력
        System.out.println("Received memid: " + memid);
        List<ShopPortfolio> liked = makeuplikesService.getAllLikedPortpolios(memid);
        for(ShopPortfolio o : liked) {
            System.out.println(o.toString());
        }
        model.addAttribute("liked", liked);
        // 뷰 반환
        return "mypage"; // mypage.html로 이동
    }

    @GetMapping("/matching")
    public String matchingPage() {
        return "matching";
    }

    @GetMapping("/result")
    public String resultPage() {
        return "matching_result";
    }

    // 회원가입
    @PostMapping("/members")
    public String addMember(Members m) {
        memberService.addMember(m);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/";
    }

    @PostMapping("/Login")
    public String memberLogin(Members m, HttpSession session) {
        System.out.println(m.toString());

        // Service를 통해 로그인 처리
        List<Members> result = memberService.authenticate(m.getMemid(), m.getMempw());

        if (result.isEmpty()) {
            // 로그인 실패: 세션에 에러 메시지 설정
            session.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        } else {
            // 로그인 성공: 세션에서 에러 메시지 제거
            session.removeAttribute("error");
            session.setAttribute("members", result.get(0)); // 로그인한 회원 정보 저장
            return "redirect:/"; // 메인 페이지로 리다이렉트
        }
    }

    // 회원정보수정
    @PostMapping("/update")
    public String updateMember(Members m, HttpSession session) {
        System.out.println(m.toString());
        Members result = memberService.updateMember(m);
        if (result != null) {
            session.setAttribute("members", result);
        }
        return "index";
    }
}
