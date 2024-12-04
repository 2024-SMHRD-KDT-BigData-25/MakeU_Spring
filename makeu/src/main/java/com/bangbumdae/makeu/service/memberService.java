package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.repository.memberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class memberService {
    @Autowired
    private final memberRepository memberRepository;
    public Members addMember(Members m) {
        return memberRepository.save(m);
    }

    public List<Members> authenticate(String memId, String memPw) {
        return memberRepository.findByMemIdAndMemPw(memId, memPw);
    }
}
