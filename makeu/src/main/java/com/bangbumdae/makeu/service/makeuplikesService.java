package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.MakeUpLikes;
import com.bangbumdae.makeu.repository.makeuplikesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class makeuplikesService {
private final makeuplikesRepository makeuplikesRepository;
    public void addLikes(String mem_id, int portfolio_idx) {
        System.out.println(mem_id);
        System.out.println(portfolio_idx);
        List<MakeUpLikes> result = makeuplikesRepository.findByMemIdAndPortfolioIdx(mem_id, portfolio_idx);
        System.out.println(result.size());
        if (result.isEmpty()) {
            makeuplikesRepository.save(new MakeUpLikes(mem_id, portfolio_idx));
        }
    }

    public List<MakeUpLikes> getLikesAll() {
        return makeuplikesRepository.findAll();
    }

    public List<Integer> getLikedPortpolios(String mem_id) {
        return makeuplikesRepository.findPortfolioIdxByMemId(mem_id);
    }
}
