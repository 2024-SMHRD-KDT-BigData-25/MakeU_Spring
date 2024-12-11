package com.bangbumdae.makeu.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.FaceType;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.PersonalColor;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.model.ShopReservation;
import com.bangbumdae.makeu.service.ReservationService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.matchingresultService;
import com.bangbumdae.makeu.service.memberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class memberController {
    private final memberService memberService;
    private final makeuplikesService makeuplikesService;
    private final matchingresultService matchingresultService;
    private final ReservationService reservationService;
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
        List<ShopPortfolio> liked = makeuplikesService.getAllLikedPortpolios(memid);
        model.addAttribute("liked", liked);
        Creator[] mathcedCreators = new Creator[3];
        mathcedCreators[0] = matchingresultService.getMatched1(memid);
        mathcedCreators[1] = matchingresultService.getMatched2(memid);
        mathcedCreators[2] = matchingresultService.getMatched3(memid);

        model.addAttribute("matched", mathcedCreators);
        
        FaceType mFaceType = memberService.getFaceType(memid);
        model.addAttribute("mFacetype", mFaceType);

        PersonalColor mPersonalColor = memberService.getPersonalColor(memid);
        model.addAttribute("mPersonalcolor", mPersonalColor);
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

    @PostMapping("reservation")
    public void addReservation(@RequestParam int shopidx, @RequestParam String reservationdatetime, @RequestParam String servicetype, @RequestParam String requirement, HttpSession session) {
        Members members = (Members)session.getAttribute("members");
        if (members == null) {
            System.out.println("로그인하세요");
            return;
        }

        ShopReservation newReservation = new ShopReservation(shopidx, members.getMemid(), Timestamp.valueOf(reservationdatetime + ":00"), servicetype, requirement);
        reservationService.addReservation(newReservation);
    }

    @PostMapping("/result")
    public String resultRecieve(@RequestParam("faceShape") String faceShape, @RequestParam("faceConfidence") float faceConfidence, @RequestParam("personalColor") String personalColor, @RequestParam("colorConfidence") float colorConfidence, HttpSession session, Model model) {
        session.setAttribute("faceShape", faceShape);
        session.setAttribute("faceConfidence", faceConfidence);
        session.setAttribute("personalColor", personalColor);
        session.setAttribute("colorConfidence", colorConfidence);
        
        model.addAttribute("faceShape", faceShape);
        model.addAttribute("faceConfidence", faceConfidence);
        model.addAttribute("personalColor", personalColor);
        model.addAttribute("colorConfidence", colorConfidence);

        return "redirect:/result";
    }
    @GetMapping("/find-password")
    public String findPasswordPage() {
        return "find_password";
    }

    @PostMapping("/find-password")
@ResponseBody
public String findPassword(@RequestParam String memid, @RequestParam String memnickname) {
    try {
        Members member = memberService.findByMemIdAndNickname(memid, memnickname);
        if (member != null) {
            return member.getMempw(); // 비밀번호 반환
        } else {
            return "틀렸습니다"; // 실패 메시지 반환
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "서버 오류 발생";
    }
}


    
}

