package com.bangbumdae.makeu.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="matching_result")
public class MatchingResult {
    @Id
    @Column(name = "resultidx")
    private  int resultidx;
    @Column(name = "memid", length = 50, nullable = false)
    private String memid;
    @Column(name = "matched1", nullable = false)
    private int matched1;
    @Column(name = "matched2", nullable = false)
    private int matched2;
    @Column(name = "matched3", nullable = false)
    private int matched3;
    @Column(name = "createdat", nullable = false)
    private Timestamp createdat = new Timestamp(System.currentTimeMillis());
}
