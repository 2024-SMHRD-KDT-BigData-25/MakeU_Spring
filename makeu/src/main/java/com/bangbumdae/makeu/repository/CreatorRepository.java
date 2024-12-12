package com.bangbumdae.makeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.Creator;


    
@Repository
public interface CreatorRepository extends JpaRepository <Creator, Integer>{
    List<Creator> findByFacetypeidxAndPersonalcoloridx(Integer facetypeidx, Integer personalcoloridx);
}

