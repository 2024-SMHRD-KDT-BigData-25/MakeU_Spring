package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.MatchingResult;

@Repository
public interface matchingresultRepository extends JpaRepository<MatchingResult, Integer>{
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.matched1 = c.creatoridx WHERE m.memid = :memid")
    Creator findMatched1ByMemid(String memid);
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.matched2 = c.creatoridx WHERE m.memid = :memid")
    Creator findMatched2ByMemid(String memid);
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.matched3 = c.creatoridx WHERE m.memid = :memid")
    Creator findMatched3ByMemid(String memid);
 
}
