package com.bangbumdae.makeu.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.MatchingResult;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.model.ShopReservation;
import com.bangbumdae.makeu.service.CreatorService;
import com.bangbumdae.makeu.service.ReservationService;
import com.bangbumdae.makeu.service.ShopInfoService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.matchingresultService;
import com.bangbumdae.makeu.service.portpolioService;
import com.bangbumdae.makeu.service.shopTagsService;
import com.bangbumdae.makeu.service.shopcartService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private final CreatorService creatorService;
    private final shopcartService shopcartService;
    private final ReservationService reservationService;
    private final matchingresultService matchingresultService;

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

    // // /matching 엔드포인트: 결과 가져오기
    // @GetMapping("/matching")
    // public ResponseEntity<List<Creator>> getAllCreators() {
    //     List<Creator> creators = creatorService.getAllCreators();
    //     if (creators.isEmpty()) {
    //         return ResponseEntity.noContent().build(); // 204 No Content
    //     }
    //     return ResponseEntity.ok(creators); // 200 OK와 결과 반환
    // }

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
    public ResponseEntity<String> saveMatchingResult(@RequestBody MatchingResult matchingResult) {
        try {
            matchingresultService.saveMatchingResult(
                matchingResult.getMemid(),
                matchingResult.getMatched1(),
                matchingResult.getMatched2(),
                matchingResult.getMatched3()
            );
            return ResponseEntity.ok("매칭 결과가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("매칭 결과 저장 중 오류가 발생했습니다.");
        }

    }
}
