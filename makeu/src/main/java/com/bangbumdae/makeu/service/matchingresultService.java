package com.bangbumdae.makeu.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.MatchingResult;
import com.bangbumdae.makeu.repository.matchingresultRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class matchingresultService {
    private final matchingresultRepository mrRepository;
    public boolean checkResult(String memid) {
        if (mrRepository.getCountBymemid(memid) > 0)
            return true;    
        return false;
    }
    public Creator getMatched1(String memid) {
        return mrRepository.findMatched1ByMemid(memid).get(0);
    }
    public Creator getMatched2(String memid) {
        return mrRepository.findMatched2ByMemid(memid).get(0);
    }
    public Creator getMatched3(String memid) {
        return mrRepository.findMatched3ByMemid(memid).get(0);
    }
    public void saveMatchingResult(String memid, int matched1, int matched2, int matched3) {
        MatchingResult matchingResult = new MatchingResult();
        matchingResult.setMemid(memid);
        matchingResult.setMatched1(matched1);
        matchingResult.setMatched2(matched2);
        matchingResult.setMatched3(matched3);
        matchingResult.setCreatedat(new Timestamp(System.currentTimeMillis()));

        mrRepository.save(matchingResult);
    }
}
