package com.bangbumdae.makeu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopCart;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.repository.shopcartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class shopcartService {
    private final shopcartRepository shopcartRepository; 
    public void addShop(ShopCart s) {
        shopcartRepository.save(s);
    }

    public List<ShopInfo> getCartShopInfo(String memid){
        return shopcartRepository.findByMemId(memid);
    }
    
    public boolean deleteCart(int cartidx, String memid) {
        Optional<ShopCart> cart = shopcartRepository.findByCartidxAndMemid(cartidx, memid);
    
        if (cart.isPresent()) {
            shopcartRepository.delete(cart.get());
            return true;
        }
        return false;
    }
}
