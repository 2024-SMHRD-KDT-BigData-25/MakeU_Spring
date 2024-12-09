package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.ShopInfo;
import java.util.List;


@Repository
public interface ShopInfoRepository extends JpaRepository<ShopInfo, Integer>{
    List<ShopInfo> findByShopidx(int shopidx);
}
