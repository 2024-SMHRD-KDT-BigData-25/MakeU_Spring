package com.bangbumdae.makeu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.model.ShopInfo;


public interface shopcartRepository extends JpaRepository<ShopCart, Integer> {
    
    
    @Query("SELECT i FROM ShopCart c JOIN ShopInfo i on c.shopidx = i.shopidx WHERE c.memid = :memid")
    List<ShopInfo> findByMemId(String memid);
    Optional<ShopCart> findByShopidxAndMemid(int shopidx, String memid);
    
}
