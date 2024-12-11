package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="Shop_info")
public class ShopInfo {
    @Id
    @Column(name = "shopidx")
    private int shopidx;
    @Column(name = "shopname", length = 100, nullable = false)
    private String shopname;
    @Column(name = "shoplocation", length = 500, nullable = false)
    private String shoplocation;
    @Column(name = "shoplon")
    private float shoplon = 0.0f;
    @Column(name = "shoplat")
    private float shoplat= 0.0f;
    @Column(name = "shopcategory", nullable = false, updatable = false)
    private int shopcategory;
    @Transient
    public int score = 0;
}
