package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.MakeUpLikes;
import com.bangbumdae.makeu.model.ShopPortfolio;
import com.bangbumdae.makeu.repository.makeuplikesRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class makeuplikesService {
private final makeuplikesRepository makeuplikesRepository;
    @Transactional
    public boolean addLikes(String mem_id, int portfolio_idx) {
        System.out.println(mem_id);
        System.out.println(portfolio_idx);
        List<MakeUpLikes> result = makeuplikesRepository.findByMemidAndPortfolioidx(mem_id, portfolio_idx);
        if (result.isEmpty()) {
            makeuplikesRepository.save(new MakeUpLikes(mem_id, portfolio_idx));
            return true;
        }
        else {
            makeuplikesRepository.deleteByMemidAndPortfolioidx(mem_id, portfolio_idx);
            return false;
        }
    }

    public List<MakeUpLikes> getLikesAll() {
        return makeuplikesRepository.findAll();
    }

    public List<Integer> getLikedPortpolios(String mem_id) {
        return makeuplikesRepository.findPortfolioidxByMemid(mem_id);
    }

    public List<ShopPortfolio> getAllLikedPortpolios(String memid) {
        return makeuplikesRepository.findLikedShopPortfolios(memid);
    }
}
