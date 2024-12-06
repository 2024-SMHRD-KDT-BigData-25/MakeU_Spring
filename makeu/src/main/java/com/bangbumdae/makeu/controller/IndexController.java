package com.bangbumdae.makeu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bangbumdae.makeu.model.MakeUpLikes;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.portpolioService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
public class IndexController {
    private final portpolioService pService;
    private final makeuplikesService makeuplikesService;
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/shopmatching")
    public String shopmatchingPage() {
        return "shopmatching";
    }
    
    
    @GetMapping("/main")
    public String mainPage(Model model, HttpSession session) {
        List<ShopPortfolio> list = pService.getPortfolios();
        model.addAttribute("portpolios", list);
        Members m = (Members)session.getAttribute("members");
        if (m != null) {
            List<MakeUpLikes> likes = makeuplikesService.getLikesAll();
            model.addAttribute("likes", likes);
        }
        return "main";
    }
}
