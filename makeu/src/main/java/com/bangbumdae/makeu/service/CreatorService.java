package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.repository.CreatorRepository;

@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;

    public CreatorService(CreatorRepository creatorRepository){
        this.creatorRepository = creatorRepository;
    }

    public List<Creator> getCreatorsByFaceTypeAndPersonalColorAndcreatorgender(Integer facetypeidx, Integer personalcoloridx, String creatorgender){
        return creatorRepository.findByFacetypeidxAndPersonalcoloridxAndCreatorgender(facetypeidx, personalcoloridx, creatorgender);
    }

    public List<Creator> getAllCreators() {
        return creatorRepository.findAll();
    }
}
