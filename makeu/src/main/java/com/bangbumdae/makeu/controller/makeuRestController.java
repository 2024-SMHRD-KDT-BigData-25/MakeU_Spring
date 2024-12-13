package com.bangbumdae.makeu.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.model.ShopReservation;
import com.bangbumdae.makeu.model.ViewEyesCreator;
import com.bangbumdae.makeu.service.ReservationService;
import com.bangbumdae.makeu.service.ShopInfoService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.matchingresultService;
import com.bangbumdae.makeu.service.memberService;
import com.bangbumdae.makeu.service.portpolioService;
import com.bangbumdae.makeu.service.shopTagsService;
import com.bangbumdae.makeu.service.shopcartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RestController
public class makeuRestController {
    // private final memberService memberService;
    private final portpolioService pService;
    private final makeuplikesService makeuplikesService;
    private final ShopInfoService shopInfoService;
    private final portpolioService portpolioService;
    private final shopTagsService shopTagsService;
    private final shopcartService shopcartService;
    private final ReservationService reservationService;
    private final matchingresultService matchingresultService;
    private final memberService memberService;

    @GetMapping("/main/list")
    public List<ShopPortfolio> getPortpolios() {
        return pService.getPortfolios();
    }

    @GetMapping("ports/{idx}")
    public List<ShopPortfolio> getMethodName(@PathVariable int idx) {
        System.out.println(idx);
        return portpolioService.getPortfolioByidx(idx);
    }

    @GetMapping("/likes/{mem_id}/{idx}")
    public boolean likes(@PathVariable String mem_id, @PathVariable int idx) {
        return makeuplikesService.addLikes(mem_id, idx);
    }

    @GetMapping("/tags/{idx}")
    public String getTags(@PathVariable int idx) {
        ShopPortfolio p = portpolioService.getPortfolio(idx);
        ShopInfo shop = shopInfoService.getShopInfo(p.getShopidx());
        String tags = "";
        char[] category = Integer.toBinaryString(shop.getShopcategory()).toCharArray();
        List<String> tagNames = shopTagsService.getTagsName();
        for (int i = 0; i < category.length; i++) {
            if (category[category.length - i - 1] == '1') {
                tags += ("#" + tagNames.get(i).strip() + ", ");
            }
        }
        return tags;
    }

    @GetMapping("/shop/{idx}")
    public ShopInfo getShopInfo(@PathVariable int idx) {
        ShopInfo result = shopInfoService.getShopInfo(idx);
        return result;
    }
    
    @PostMapping("/update_pos")
    public void updatePos(@RequestBody ShopInfo entity) {
        // TODO: process POST request
        System.out.println(entity.toString());
        shopInfoService.updateShopInfo(entity);
    }

    @GetMapping("/shop-cart")
    public List<ShopInfo> getCartShopInfo(HttpSession session, Model model) {
        Members members = (Members) session.getAttribute("members");
        String memid = members.getMemid();
        List<ShopInfo> cart = shopcartService.getCartShopInfo(memid);
        System.out.println(cart.size());
        return cart;
    }

    @PostMapping("reservation")
    public String addReservation(@RequestParam int shopidx, @RequestParam String reservationdatetime, @RequestParam String servicetype, @RequestParam String requirement, HttpSession session) {
        Members members = (Members)session.getAttribute("members");
        if (members == null) {
            System.out.println("로그인하세요");
            return "error!!";
        }

        ShopReservation newReservation = new ShopReservation(shopidx, members.getMemid(), Timestamp.valueOf(reservationdatetime + ":00"), servicetype, requirement);
        reservationService.addReservation(newReservation);
        return members.getMemid();
    }
    
    @PostMapping("/save")
    public String saveMatchingResult(@RequestParam String faceShape, @RequestParam String personalColor, HttpSession session) {
        List<ViewEyesCreator> creators = (List<ViewEyesCreator>) session.getAttribute("creators");
        Members member = (Members) session.getAttribute("members");
        
        if(member == null){
            return "login_error";
        }
        
        int matched1 = (creators.size() > 0) ? creators.get(0).getCreatoridx() : null;
        int matched2 = (creators.size() > 1) ? creators.get(1).getCreatoridx() : null;
        int matched3 = (creators.size() > 2) ? creators.get(2).getCreatoridx() : null;

        // 매칭 결과 저장
        matchingresultService.saveMatchingResult(member.getMemid(), matched1, matched2, matched3);

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

        //퍼컬, 얼굴형 정보 저장
        member.setFacetypeidx(faceShapeMap.get(faceShape));
        member.setPersonalcoloridx(personalColorMap.get(personalColor));
        memberService.updateMember(member);

        return "save_success"; // 성공 처리 (예: 성공 메시지나 페이지 리다이렉션)
    }

    @PostMapping("/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Integer reservationId, HttpSession session) {
        System.out.println("취소 요청 - 예약 ID: " + reservationId); // 로그 추가
        Members member = (Members) session.getAttribute("members");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
    
        boolean isCancelled = reservationService.cancelReservation(reservationId, member.getMemid());
        if (isCancelled) {
            return ResponseEntity.ok("예약이 취소되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("예약 취소에 실패했습니다.");
        }
    }
}


