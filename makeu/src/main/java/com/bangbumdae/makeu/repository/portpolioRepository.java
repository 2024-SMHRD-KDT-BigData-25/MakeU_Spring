package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.ShopPortfolio;

@Repository
public interface portpolioRepository  extends JpaRepository<ShopPortfolio, Integer>{

}