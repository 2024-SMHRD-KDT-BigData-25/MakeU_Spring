package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.ShopInfo;

@Repository
public interface ShopInfoRepository extends JpaRepository<ShopInfo, Integer>{

}
