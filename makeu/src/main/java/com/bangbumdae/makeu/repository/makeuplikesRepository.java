package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.MakeUpLikes;
import java.util.List;


@Repository
public interface makeuplikesRepository extends JpaRepository<MakeUpLikes, Integer> {
    List<MakeUpLikes> findByMemIdAndPortfolioIdx(String memId, int portfolioIdx);
}
