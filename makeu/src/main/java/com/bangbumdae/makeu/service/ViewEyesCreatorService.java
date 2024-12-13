package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ViewEyesCreator;
import com.bangbumdae.makeu.repository.ViewEyesCreatorRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ViewEyesCreatorService {
    private final ViewEyesCreatorRepository viewEyesCreatorRepository;
    public List<ViewEyesCreator> findByCreatorgender(String creatorgender) {
        return viewEyesCreatorRepository.findByCreatorgender(creatorgender);
    }
}
