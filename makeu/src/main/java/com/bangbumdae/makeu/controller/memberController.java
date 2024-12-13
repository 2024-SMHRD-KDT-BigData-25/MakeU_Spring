package com.bangbumdae.makeu.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.FaceType;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.PersonalColor;
import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.model.ShopReservation;
import com.bangbumdae.makeu.model.ViewEyesCreator;
import com.bangbumdae.makeu.service.shopcartService;
import com.bangbumdae.makeu.service.ReservationService;
import com.bangbumdae.makeu.service.ViewEyesCreatorService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.matchingresultService;
import com.bangbumdae.makeu.service.memberService;
import com.bangbumdae.makeu.service.CreatorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class memberController {
    private final memberService memberService;
    private final makeuplikesService makeuplikesService;
    private final matchingresultService matchingresultService;
    private final ReservationService reservationService;
    private final shopcartService shopcartService;
    private final CreatorService creatorService;
    private final ViewEyesCreatorService viewEyesCreatorService;

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

        List<ShopReservation> reservations = reservationService.getReservations(memid);
        model.addAttribute("reservations", reservations);
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
        return "Login";
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

    // 장바구니 추가
    @PostMapping("/shops")
    public String addShop(@RequestParam int shopIdx, HttpSession session) {
        Members mem = (Members) session.getAttribute("members");
        if (mem == null) {
            System.out.println("로그인을 해주세요");
            return "redirect:/";
        }

        shopcartService.addShop(new ShopCart(mem.getMemid(), shopIdx));
        return "index";
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
     // /result 엔드포인트: 결과 조회
    @PostMapping("/result")
    public String getCreators(@RequestBody HashMap<String, String> request, HttpSession session) {
        String faceShape = request.get("faceShape");
        String personalColor = request.get("personalColor");
        String gender = request.get("gender");
        float angle = Float.parseFloat(request.get("eye"));

        // Mapping faceShape와 personalColor 값을 인덱스로 변환
        Map<String, Integer> faceShapeMap = Map.of(
            "Heart", 1,
            "Oval", 2,
            "Oblong", 3,
            "Round", 4,
            "Square", 5
        );

        Map<String, Integer> personalColorMap = Map.of(
            "Spring", 1,
            "Summer", 2,
            "Autumn", 3,
            "Winter", 4
        );

        Map<String, String> genderMap = Map.of(
            "female", "F",
            "male", "M" 
        );

        // String 값을 Integer로 변환
        int faceTypeIdx = faceShapeMap.get(faceShape);
        int personalColorIdx = personalColorMap.get(personalColor);
        String creatorGender = genderMap.get(gender);


        System.out.println(gender);
        List<ViewEyesCreator> sameGender = viewEyesCreatorService.findByCreatorgender(creatorGender);                
        
        // 퍼스널 컬러, 얼굴형별 점수 부여
        for (ViewEyesCreator temp : sameGender) {
            if (temp.getFacetypeidx() == faceTypeIdx) { 
                temp.score += 5;
            }
            if (temp.getPersonalcoloridx() == personalColorIdx) {
                temp.score += 10;
            }
            
            temp.eye_val = Math.abs(temp.eye_val - angle);
        }

        sameGender.sort((o1, o2) -> Float.compare(o1.eye_val, o2.eye_val));

        // 눈 각도 값차이가 작은 순서대로 점수 차등 부여
        int point = 20;
        for (int i = 0; i < 10; i++) {
            sameGender.get(i).score += point;
            point-=2;
        }

        sameGender.sort((o1, o2) -> Integer.compare(o2.score, o1.score));
        // 결과 처리
        if (sameGender.isEmpty()) {
            return "error"; // 204 No Content
        }
        
        session.setAttribute("creators", sameGender.subList(0, 3));

        return "matching_result"; // 200 OK
    }
}
