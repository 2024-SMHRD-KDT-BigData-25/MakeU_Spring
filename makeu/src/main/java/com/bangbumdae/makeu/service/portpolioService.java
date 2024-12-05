package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.repository.portpolioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class portpolioService {
    private final portpolioRepository portpolioRepository;
    public List<ShopPortfolio> getPortfolios() {
        return portpolioRepository.findAll();
    }
}
