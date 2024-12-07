package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.MakeUpLikes;
import com.bangbumdae.makeu.model.ShopPortfolio;

import java.util.List;

@Repository
public interface makeuplikesRepository extends JpaRepository<MakeUpLikes, Integer> {
    List<MakeUpLikes> findByMemidAndPortfolioidx(String memid, int portfolioidx);
    void deleteByMemidAndPortfolioidx(String memid, int portfolioidx);
   @Query("SELECT m.portfolioidx FROM MakeUpLikes m WHERE m.memid = :memid")
    List<Integer> findPortfolioidxByMemid(@Param("memid") String memid);
    List<MakeUpLikes> findByMemid(String memid);

    @Query("SELECT p.portfolioidx, p.shopidx, p.portfolioimg, p.createdat FROM MakeUpLikes l JOIN ShopPortfolio p ON l.portfolioidx = p.portfolioidx WHERE l.memid = :memid")
    List<ShopPortfolio> findLikedShopPortfolios(@Param("memid") String memid);

}
