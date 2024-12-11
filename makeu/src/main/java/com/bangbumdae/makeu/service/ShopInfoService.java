package com.bangbumdae.makeu.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopInfo;
import com.bangbumdae.makeu.model.ShopTags;
import com.bangbumdae.makeu.repository.ShopInfoRepository;
import com.bangbumdae.makeu.repository.makeuplikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopInfoService {
    private final ShopInfoRepository shopInfoRepository;
    private final makeuplikesRepository makeuplikesRepository;
    private final shopTagsService shopTagsService;
    
    public ShopInfo getShopInfo(int idx) {
        return shopInfoRepository.findByShopidx(idx).get(0);
    }
    public List<ShopInfo> getList(){
        return shopInfoRepository.findAll();
    }

    public List<ShopInfo> getList(String memid){
        List<ShopInfo> liked = makeuplikesRepository.likedShops(memid);
        List<ShopInfo> shops = shopInfoRepository.findAll(Sort.by("shopidx").ascending());

        List<ShopTags> tagList = shopTagsService.getCShopTags();

        for (ShopInfo s : liked) {
            ShopInfo temp = shops.get(s.getShopidx() - 1);
            temp.score += 10;

            char[] category = Integer.toBinaryString(s.getShopcategory()).toCharArray();
            for (int i = 0; i < category.length; i++) {
                if (category[category.length - i - 1] == '1') {
                    tagList.get(i).score++;
                }
            }
        }
        tagList.sort((o1, o2) -> Double.compare(o2.score, o1.score));
        

        for (ShopInfo s : shops) {
            int temp = s.getShopcategory();
            for (int i = 0; i < 5; i++) {
                if ((temp & (int) Math.pow(2, tagList.get(i).getTagidx())) != 0) {
                    // 조건이 true일 경우 실행
                    s.score += 5;
                }
            }
        }

        shops.sort((s1, s2) -> Integer.compare(s2.score, s1.score));

        for (ShopInfo s : shops) {
            System.out.println(s.getShopidx() + " " + s.getShopname() + " " + s.score);
        }
        return shops;
    }

    public void updateShopInfo(ShopInfo s) {
        shopInfoRepository.save(s);
    }
}
