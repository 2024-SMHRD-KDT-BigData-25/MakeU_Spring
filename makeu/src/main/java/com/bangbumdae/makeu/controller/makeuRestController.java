package com.bangbumdae.makeu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.ShopInfoService;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.portpolioService;
import com.bangbumdae.makeu.service.shopTagsService;

import lombok.RequiredArgsConstructor;
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
    @GetMapping("/main/list")
    public List<ShopPortfolio> getPortpolios() {
        return pService.getPortfolios();
    }

    @GetMapping("/likes/{mem_id}/{idx}")
    public boolean likes(@PathVariable String mem_id, @PathVariable int idx) {
        return makeuplikesService.addLikes(mem_id, idx);
    }
    @GetMapping("/tags/{idx}")
    public String getTags(@PathVariable int idx) {
        ShopPortfolio p = portpolioService.gePortfolio(idx);
        ShopInfo shop = shopInfoService.getShopInfo(p.getShopidx());
        System.out.println(shop.toString());
        String tags = "";
        char[] category = Integer.toBinaryString(shop.getShopcategory()).toCharArray();
        List<String> tagNames = shopTagsService.getTagsName();
        for (int i = 0; i < category.length; i++) {
            if (category[category.length - i - 1] == '1') {
                tags += ("#" + tagNames.get(i).strip()+ ", ");
            }
        }
        return tags;
    }
    
}
