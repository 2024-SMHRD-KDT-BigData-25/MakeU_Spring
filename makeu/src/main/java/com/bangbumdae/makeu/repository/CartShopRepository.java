package com.bangbumdae.makeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.CartShop;

@Repository
public interface CartShopRepository extends JpaRepository<CartShop, Integer> {
    List<CartShop> findByMemid(String memid);
}
