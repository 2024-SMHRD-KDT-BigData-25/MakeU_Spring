package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="shop_cart")
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {
    @Id
    @Column(name = "cartidx")
    private  int cartidx;
    @Column(name = "memid", length = 50, nullable = false)
    private String memid;
    @Column(name = "shopidx", nullable = false)
    private  int shopidx;

    public ShopCart(String memid, int shopidx) {
        this.memid = memid;
        this.shopidx = shopidx;
    }
}
