package com.bangbumdae.makeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.ShopCart;


public interface shopcartRepository extends JpaRepository<ShopCart, Integer> {
    
    //특정 회원의 장바구니 목록 조회
    // @Query("SELECT i.shopname, i.shoplocation FROM shop_cart c JOIN shop_info i WHERE i.shopidx = :shopidx")
    // List<Members> findByMemberId(int shopidx);

    // // 특정 제품이 장바구니에 이미 있는지 확인
    // @Query("SELECT c FROM Cart c WHERE c.member.memid = :memid AND c.product.id = :productId")
    // ShopCart findByMemberIdAndProductId(String memid, int shopid);
    
    // 장바구니에 제품 추가 (이미 존재하지 않으면 추가)
    // @Modifying
    // @Transactional
    // @Query("INSERT INTO shop_cart(memid, shopid) VALUES (:memid, :shopid)")
    // void addProductToCart(String memid, int shopid);
}
