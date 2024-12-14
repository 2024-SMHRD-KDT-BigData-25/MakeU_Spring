package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.CartShop;
import com.bangbumdae.makeu.repository.CartShopRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartShopService {
    private final CartShopRepository cartShopRepository;
    public List<CartShop> findCartShopsByMemid(String memid) {
        return cartShopRepository.findByMemid(memid);
    }
}
