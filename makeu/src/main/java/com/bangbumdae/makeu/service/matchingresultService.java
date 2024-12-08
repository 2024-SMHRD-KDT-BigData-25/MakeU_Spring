package com.bangbumdae.makeu.service;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.repository.matchingresultRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class matchingresultService {
    private final matchingresultRepository mrRepository;
    public Creator getMatched1(String memid) {
        return mrRepository.findMatched1ByMemid(memid);
    }
    public Creator getMatched2(String memid) {
        return mrRepository.findMatched2ByMemid(memid);
    }
    public Creator getMatched3(String memid) {
        return mrRepository.findMatched3ByMemid(memid);
    }
    
}
