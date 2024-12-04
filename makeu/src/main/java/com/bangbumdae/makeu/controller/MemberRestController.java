package com.bangbumdae.makeu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.repository.memberRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class MemberRestController {
    private final memberRepository memberRepository;

    
}
