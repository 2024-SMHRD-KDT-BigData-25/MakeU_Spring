package com.bangbumdae.makeu.service;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.repository.ShopInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopInfoService {
private final ShopInfoRepository shopInfoRepository;
}
