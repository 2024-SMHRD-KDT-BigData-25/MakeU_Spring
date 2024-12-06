package com.bangbumdae.makeu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.service.makeuplikesService;
import com.bangbumdae.makeu.service.portpolioService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
public class makeuRestController {
    // private final memberService memberService;
    private final portpolioService pService;
    private final makeuplikesService makeuplikesService;
    @GetMapping("/main/list")
    public List<ShopPortfolio> getPortpolios() {
        return pService.getPortfolios();
    }

    @GetMapping("/likes/{mem_id}/{idx}")
    public ResponseEntity<String> likes(@PathVariable String mem_id, @PathVariable int idx) {
        makeuplikesService.addLikes(mem_id, idx);
        return ResponseEntity.ok("좋아요 추가 완료!"); // JSON 응답 반환
    }
}
