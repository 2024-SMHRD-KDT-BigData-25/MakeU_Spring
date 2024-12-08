package com.bangbumdae.makeu.service;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.repository.ShopInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopInfoService {
    private final ShopInfoRepository shopInfoRepository;
    public ShopInfo getShopInfo(int idx) {
        return shopInfoRepository.findByShopidx(idx).get(0);
    }
}
