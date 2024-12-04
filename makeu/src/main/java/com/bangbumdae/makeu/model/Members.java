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
    @Column(name = "mem_id")
    private String mem_id;
    @Column(length = 50, nullable = false)
    private String mem_pw;
    @Column(length = 50, nullable = false)
    private String mem_nickname;
    @Column(length = 50, nullable = false)
    private String mem_name;
    private int personal_color_idx;
    private int face_type_idx;
}
