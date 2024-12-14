package com.bangbumdae.makeu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_shop") // View 이름
public class CartShop {
    @Id
    private  int cartidx;
    private String memid;
    private  int shopidx;
    private String shopname;
    private String shoplocation;
}
