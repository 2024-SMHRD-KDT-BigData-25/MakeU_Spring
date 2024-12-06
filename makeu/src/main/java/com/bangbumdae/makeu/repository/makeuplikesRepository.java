package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.MakeUpLikes;
import java.util.List;

@Repository
public interface makeuplikesRepository extends JpaRepository<MakeUpLikes, Integer> {
    List<MakeUpLikes> findByMemIdAndPortfolioIdx(String memId, int portfolioIdx);
    void deleteByMemIdAndPortfolioIdx(String memId, int portfolioIdx);
   @Query("SELECT m.portfolioIdx FROM MakeUpLikes m WHERE m.memId = :memId")
    List<Integer> findPortfolioIdxByMemId(@Param("memId") String memId);

}
