package com.bangbumdae.makeu.service;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.repository.memberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class memeberService {
    private final memberRepository memberRepository;
    public Members addMember(Members m) {
        return memberRepository.save(m);
    }
}
