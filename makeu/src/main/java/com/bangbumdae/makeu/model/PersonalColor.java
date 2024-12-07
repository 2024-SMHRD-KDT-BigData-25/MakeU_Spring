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
    @Column(name = "personalcoloridx")
    private int personalcoloridx;
    @Column(name = "personalcolorname", length = 50, nullable = false)
    private String personalcolorname;
    @Column(name = "personalcolordescription", length = 1000)
    private String personalcolordescription;
}
