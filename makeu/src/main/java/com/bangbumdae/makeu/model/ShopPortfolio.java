package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="shop_portfolio")
public class ShopPortfolio {
    @Id
    @Column(name = "portfolio_idx")
    private int portfoliIdx;
    @Column(name = "shop_idx", nullable = false)
    private int shopIdx;
    @Column(name = "portfolio_img", length = 500, nullable = false)
    private String portfolioImg;
    @Column(name = "created_at")
    private String createdAt;
}
