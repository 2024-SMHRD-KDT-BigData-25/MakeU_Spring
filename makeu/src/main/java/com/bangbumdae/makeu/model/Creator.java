package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="creator")
public class Creator {
    @Id
    @Column(name = "creatoridx")
    private int creatoridx;
    @Column(name = "creatorname", length = 50, nullable = false)
    private String creatorname;
    @Column(name = "creatorgender", length = 1, nullable = false)
    private String creatorgender;
    @Column(name = "creatorlink", length = 100, nullable = false)
    private String creatorlink;
    @Column(name = "facetypeidx")
    private int facetypeidx;
    @Column(name = "personalcoloridx")
    private int personalcoloridx;
}
