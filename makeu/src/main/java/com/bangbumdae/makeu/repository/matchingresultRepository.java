package com.bangbumdae.makeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.MatchingResult;

@Repository
public interface matchingresultRepository extends JpaRepository<MatchingResult, Integer>{
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.creatoridx = c.creatoridx WHERE m.memid = :memid")
    List<Creator> findMatched1ByMemid(String memid);
}
