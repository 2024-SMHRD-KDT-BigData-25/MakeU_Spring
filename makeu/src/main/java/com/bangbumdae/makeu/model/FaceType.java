package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="face_type")
public class FaceType {
    @Id
    @Column(name = "face_type_idx")
    private int faceTypeIdx;
    @Column(name = "face_type_name", length = 50, nullable = false)
    private String faceTypeName;
}
