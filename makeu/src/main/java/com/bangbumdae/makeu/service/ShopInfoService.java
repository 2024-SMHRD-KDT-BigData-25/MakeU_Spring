package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.MakeUpLikes;
import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.repository.ShopInfoRepository;
import com.bangbumdae.makeu.repository.makeuplikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopInfoService {
    private final ShopInfoRepository shopInfoRepository;
    private final makeuplikesRepository makeuplikesRepository;
    
    public ShopInfo getShopInfo(int idx) {
        return shopInfoRepository.findByShopidx(idx).get(0);
    }
    public List<ShopInfo> getList(){
        return shopInfoRepository.findAll();
    }

    public List<ShopInfo> getList(String memid){
        List<ShopInfo> liked = makeuplikesRepository.likedShops(memid);
        for (ShopInfo s : liked) {
            
        }

        List<ShopInfo> shops = shopInfoRepository.findAll(Sort.by("shopidx").ascending());
        
        return shopInfoRepository.findAll();
    }

    public void updateShopInfo(ShopInfo s) {
        shopInfoRepository.save(s);
    }
}
