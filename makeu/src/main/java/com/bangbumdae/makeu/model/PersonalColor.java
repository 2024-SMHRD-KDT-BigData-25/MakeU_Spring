package com.bangbumdae.makeu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="personal_color")
public class PersonalColor {
    @Id
    @Column(name = "personal_color_idx")
    private int personalColorIdx;
    @Column(name = "personal_color_name", length = 50, nullable = false)
    private String personalColorName;
    @Column(name = "personal_color_description", length = 1000)
    private String personalColorDescription;
}
