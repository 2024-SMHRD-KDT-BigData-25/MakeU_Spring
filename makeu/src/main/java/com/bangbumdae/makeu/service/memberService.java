package com.bangbumdae.makeu.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.repository.memberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class memberService {
    private final memberRepository memberRepository;
    public Members addMember(Members m) {
        return memberRepository.save(m);
    }

    public List<Members> authenticate(String memId, String memPw) {
        return memberRepository.findByMemIdAndMemPw(memId, memPw);
    }

    @Transactional
    public Members updateMember(Members updatedMember) {
       return memberRepository.save(updatedMember); // 기존 Primary Key가 존재하면 업데이트 처리
    }
}
