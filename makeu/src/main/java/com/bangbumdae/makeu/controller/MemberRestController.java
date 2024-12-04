package com.bangbumdae.makeu.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bangbumdae.makeu.service.memberService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;



@RequiredArgsConstructor
@RestController
public class MemberRestController {
    @Autowired
    private final memberService memberService;
    
    
}
