package com.bangbumdae.makeu.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_reservation")
public class ShopReservation {
@Id
@Column(name = "reservationidx")
private int reservationidx;
@Column(name = "shopidx")
private int shopidx;
@Column(name = "memid", length = 50)
private String memid;
@Column(name = "reservationdatetime")
private Timestamp reservationdatetime;
@Column(name = "servicetype", length = 50)
private String servicetype;
@Column(name = "iscanceled")
private int iscanceled;
@Column(name = "requirements")
private String requirements;
@Column(name = "createdat")
private Timestamp createdat = new Timestamp(System.currentTimeMillis());

@Transient
private String shopname;

public ShopReservation(int shopidx, String memid, Timestamp reservationdatetime, String servicetype, String requirements) {
    this.shopidx = shopidx;
    this.memid = memid;
    this.reservationdatetime = reservationdatetime;
    this.servicetype = servicetype;
    this.requirements = requirements;
}
}
