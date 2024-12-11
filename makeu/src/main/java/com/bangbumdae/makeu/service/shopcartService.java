package com.bangbumdae.makeu.service;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.repository.shopcartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class shopcartService {
    private final shopcartRepository shopcartRepository; 
    public void addShop(ShopCart s) {
        shopcartRepository.save(s);
    }
}
