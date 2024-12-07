package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="members")
public class Members {
    @Id
    @Column(name = "memid")
    private String memid;
    @Column(name = "mempw", length = 50, nullable = false)
    private String mempw;
    @Column(name = "memnickname", length = 50, nullable = false)
    private String memnickname;
    @Column(name = "memname", length = 50, nullable = false)
    private String memname;

    @Column(name = "personalcoloridx")
    private int personalcoloridx = 0;

    @Column(name = "facetypeidx")
    private int facetypeidx = 0;
}
