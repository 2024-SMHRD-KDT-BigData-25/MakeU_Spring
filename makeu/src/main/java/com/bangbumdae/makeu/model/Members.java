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
    private String memId;
    @Column(name = "mem_pw", length = 50, nullable = false)
    private String memPw;
    @Column(name = "mem_nickname", length = 50, nullable = false)
    private String memNickname;
    @Column(name = "mem_name", length = 50, nullable = false)
    private String memName;

    @Column(name = "personal_color_idx")
    private int personalColorIdx = 0;

    @Column(name = "face_type_idx")
    private int faceTypeIdx = 0;
}
