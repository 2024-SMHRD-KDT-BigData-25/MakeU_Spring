package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.PortpolioTags;
import com.bangbumdae.makeu.repository.PortpolioTagsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PortpolioTagsService {
    private final PortpolioTagsRepository portpolioTagsRepository;
    public List<PortpolioTags> getPortpolioTags() {
        return portpolioTagsRepository.findAll();
    }
}
