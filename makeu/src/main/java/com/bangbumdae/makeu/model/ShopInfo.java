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
    @Column(name = "shopidx")
    private int shopidx;
    @Column(name = "shopname", length = 100, nullable = false)
    private int shopname;
    @Column(name = "shoplocation", length = 500, nullable = false)
    private String shoplocation;
    @Column(name = "shopcategory", nullable = false, updatable = false)
    private int shopcategory;
}
