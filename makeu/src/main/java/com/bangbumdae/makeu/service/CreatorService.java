package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.repository.CreatorRepository;

@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;

    public CreatorService(CreatorRepository creatorRepository){
        this.creatorRepository = creatorRepository;
    }

    public List<Creator> getCreatorsByFaceTypeAndPersonalColor(Integer facetypeidx, Integer personalcoloridx){
        return creatorRepository.findByFacetypeidxAndPersonalcoloridx(facetypeidx, personalcoloridx);
    }

    public List<Creator> getAllCreators() {
        return creatorRepository.findAll();
    }
}
