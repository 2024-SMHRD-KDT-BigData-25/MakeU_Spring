package com.bangbumdae.makeu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.CreatorService;
import com.bangbumdae.makeu.service.ShopInfoService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.portpolioService;
import com.bangbumdae.makeu.service.shopTagsService;
import com.bangbumdae.makeu.service.shopcartService;

import org.springframework.ui.Model;
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
    private final CreatorService creatorService;
    private final shopcartService shopcartService;

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

    @PostMapping("/result")
    public ResponseEntity<List<Creator>> getCreators(@RequestBody Map<String, String> request) {
        
        String faceShape = request.get("faceShape");
        String personalColor = request.get("personalColor");

        // Mapping faceShape와 personalColor 값을 인덱스로 변환
        Map<String, Integer> faceShapeMap = Map.of(
            "Heart", 1,
            "Oval", 2,
            "Oblong", 3,
            "Round", 4,
            "Square",5
        );

        Map<String, Integer> personalColorMap = Map.of(
            "Spring", 1,
            "Summer", 2,
            "Autumn", 3,
            "Winter", 4
        );
        
        Integer facetypeidx = faceShapeMap.get(faceShape);
        Integer personalcoloridx = personalColorMap.get(personalColor);

        if (facetypeidx == null || personalcoloridx == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Creator> creators = creatorService.getCreatorsByFaceTypeAndPersonalColor(facetypeidx, personalcoloridx);
        return ResponseEntity.ok(creators);
    }

    @GetMapping("/shop-cart")
    public List<ShopInfo> getCartShopInfo(HttpSession session, Model model) {
        Members members = (Members) session.getAttribute("members");
        String memid = members.getMemid();
        List<ShopInfo> cart = shopcartService.getCartShopInfo(memid);
        System.out.println(cart.size());
        return cart;
    }
}
