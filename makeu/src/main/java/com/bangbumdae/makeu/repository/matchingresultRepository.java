package com.bangbumdae.makeu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.Creator;
import com.bangbumdae.makeu.model.MatchingResult;

@Repository
public interface matchingresultRepository extends JpaRepository<MatchingResult, Integer>{
    @Query("SELECT COUNT(m) FROM MatchingResult m where m.memid = :memid")
    int getCountBymemid(String memid);
    Optional<MatchingResult> findByMemid(String memid);
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.matched1 = c.creatoridx WHERE m.memid = :memid order by m.createdat DESC")
    List<Creator> findMatched1ByMemid(String memid);
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.matched2 = c.creatoridx WHERE m.memid = :memid order by m.createdat DESC")
    List<Creator> findMatched2ByMemid(String memid);
    @Query("SELECT c FROM MatchingResult m JOIN Creator c  ON m.matched3 = c.creatoridx WHERE m.memid = :memid order by m.createdat DESC")
    List<Creator> findMatched3ByMemid(String memid);
 
}
