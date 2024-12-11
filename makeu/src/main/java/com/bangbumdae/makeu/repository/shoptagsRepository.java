package com.bangbumdae.makeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.ShopTags;

@Repository
public interface shoptagsRepository extends JpaRepository<ShopTags, Integer> {
    @Query("SELECT t.tagname from ShopTags t order by t.tagidx")
    List<String> findTagnameList();
}
