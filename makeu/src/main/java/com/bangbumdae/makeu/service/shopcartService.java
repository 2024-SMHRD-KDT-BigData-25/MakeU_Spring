package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.repository.shopcartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class shopcartService {
    private final shopcartRepository shopcartRepository; 
    public void addShop(ShopCart s) {
        shopcartRepository.save(s);
    }

    public List<ShopInfo> getCartShopInfo(String memid){
        return shopcartRepository.findByMemId(memid);
    }
}
