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
    @Column(name = "portfolioidx")
    private int portfolioidx;
    @Column(name = "shopidx", nullable = false)
    private int shopidx;
    @Column(name = "portfolioimg", length = 500, nullable = false)
    private String portfolioimg;
    @Column(name = "createdat")
    private String createdat;
}
