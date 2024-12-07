package com.bangbumdae.makeu.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="make_up_likes")
@AllArgsConstructor
@NoArgsConstructor
public class MakeUpLikes {
    @Id
    @Column(name = "likesidx")
    private int likesidx;
    @Column(name = "memid", length = 50, nullable = false)
    private String memid;
    @Column(name = "portfolioidx", nullable = false)
    private int portfolioidx;

    @Column(name = "createdat", nullable = false)
    private Timestamp createdat;  // Timestamp 사용

    public MakeUpLikes(String memid, int portfolioIdx) {
        this.memid = memid;
        this.portfolioidx = portfolioIdx;
        this.createdat = new Timestamp(System.currentTimeMillis()); // 현재 시간 기본 설정
    }
}
