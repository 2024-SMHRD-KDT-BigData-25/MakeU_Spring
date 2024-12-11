package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "shop_tags")
public class ShopTags {
    @Id
    @Column(name = "tagidx")
    private int tagidx;
    @Column(name = "tagname", length = 20)
    private String tagname;
    @Transient
    public int score = 0;
}
