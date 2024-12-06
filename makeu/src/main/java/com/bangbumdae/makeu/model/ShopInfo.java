package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Shop_info")
public class ShopInfo {
    @Id
    @Column(name = "shop_idx")
    private int shopIdx;
    @Column(name = "shop_name", length = 100, nullable = false)
    private int shopName;
    @Column(name = "shop_location", length = 500, nullable = false)
    private String shopLocation;
    @Column(name = "shop_category", nullable = false, updatable = false)
    private int shopCategory;
}
