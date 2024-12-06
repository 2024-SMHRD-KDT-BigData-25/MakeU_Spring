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
    @Column(name = "likes_idx")
    private int likesIdx;
    @Column(name = "mem_id", length = 50, nullable = false)
    private String memId;
    @Column(name = "portfolio_idx", nullable = false)
    private int portfolioIdx;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;  // Timestamp 사용

    public MakeUpLikes(String memId, int portfolioIdx) {
        this.memId = memId;
        this.portfolioIdx = portfolioIdx;
        this.createdAt = new Timestamp(System.currentTimeMillis()); // 현재 시간 기본 설정
    }
}
