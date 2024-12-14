package com.bangbumdae.makeu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.FaceType;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.PersonalColor;
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

    public List<Members> authenticate(String memid, String memPw) {
        return memberRepository.findByMemidAndMempw(memid, memPw);
    }

    public Members findMemberByMemid(String memid) {
        Optional<Members> m = memberRepository.findById(memid);
        if (m.isPresent()) {
            return m.get();
        }
        return null;
    }

    @Transactional
    public Members updateMember(Members updatedMember) {
       return memberRepository.save(updatedMember); // 기존 Primary Key가 존재하면 업데이트 처리
    }

    public PersonalColor getPersonalColor(String memid) {
        return memberRepository.findPersonalColorByMemid(memid);
    }
    public FaceType getFaceType(String memid) {
        return memberRepository.findFacetypeByMemid(memid);
    }
    public Members findByMemIdAndNickname(String memid, String memnickname) {
        List<Members> members = memberRepository.findByMemidAndMemnickname(memid, memnickname);
        if (members.isEmpty()) {
            return null; // 결과가 없을 경우 null 반환
        }
        return members.get(0); // 첫 번째 결과 반환
    }
    
    public void quitMember(Members m) {
        memberRepository.delete(m);
    }
}
