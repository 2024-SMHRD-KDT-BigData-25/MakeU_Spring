package com.bangbumdae.makeu.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.portpolioService;
import com.bangbumdae.makeu.service.ShopInfoService;
import com.bangbumdae.makeu.service.shopTagsService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final portpolioService pService;
    private final makeuplikesService makeuplikesService;
    private final ShopInfoService shopInfoService;
    private final shopTagsService shopTagsService;
    @GetMapping("/shopmatching")
    public String shopmatchingPage(Model model, HttpSession session) {
        List<ShopInfo> slist;
        Members member= (Members)session.getAttribute("members");
        if (member != null) {
            slist = shopInfoService.getList(member.getMemid());
        }
        else  {
            slist = shopInfoService.getList();
        }
        
        model.addAttribute("slist", slist);
        String[] tags = new String[slist.size()];
        Arrays.fill(tags, "");
    
        List<String> tagNames = shopTagsService.getTagsName();
        for (int i = 0; i < tags.length; i++) {
            char[] category = Integer.toBinaryString(slist.get(i).getShopcategory()).toCharArray();
            
            for (int j = 0; j < category.length; j++) {
                if (category[category.length - j - 1] == '1') {
                    tags[i] += ("#" + tagNames.get(j).strip()+ ", ");
                }
            }
        }
        model.addAttribute("shop_tags", tags);
        return "shopmatching";
    }
    
    
    @GetMapping("/")
    public String mainPage(Model model, HttpSession session) {
        List<ShopPortfolio> list = pService.getPortfolios();
        model.addAttribute("portpolios", list);
        Members m = (Members)session.getAttribute("members");
        if (m != null) {
            List<Integer> likes = makeuplikesService.getLikedPortpolios(m.getMemid());
            model.addAttribute("likes", likes);
        }
        return "main";
    }
}
