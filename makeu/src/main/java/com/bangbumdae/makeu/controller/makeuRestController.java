package com.bangbumdae.makeu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.ShopInfoService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.portpolioService;
import com.bangbumdae.makeu.service.shopTagsService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
public class makeuRestController {
    // private final memberService memberService;
    private final portpolioService pService;
    private final makeuplikesService makeuplikesService;
    private final ShopInfoService shopInfoService;
    private final portpolioService portpolioService;
    private final shopTagsService shopTagsService;

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

    // @GetMapping("/shop")
    // public List<ShopInfo> getShoplist(HttpSession session) {
    //     Members member = (Members)session.getAttribute("members");
    //     if (member == null)
    //         return shopInfoService.getList();
        
        
    // }

    @PostMapping("/update_pos")
    public void updatePos(@RequestBody ShopInfo entity) {
        // TODO: process POST request
        System.out.println(entity.toString());
        shopInfoService.updateShopInfo(entity);
    }

}
