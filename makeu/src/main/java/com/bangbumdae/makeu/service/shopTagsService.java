package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopTags;
import com.bangbumdae.makeu.repository.shoptagsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class shopTagsService {
    private final shoptagsRepository shoptagsRepository;
    public List<String> getTagsName() {
        return shoptagsRepository.findTagnameList();
    }
    public List<ShopTags> getCShopTags() {
        return shoptagsRepository.findAll(Sort.by("tagidx").ascending());
    }
}
