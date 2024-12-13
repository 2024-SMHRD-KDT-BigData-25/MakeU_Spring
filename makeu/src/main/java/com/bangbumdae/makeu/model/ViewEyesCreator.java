package com.bangbumdae.makeu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "view_eyes_creator") // View 이름
public class ViewEyesCreator {

    @Id
    private int creatoridx;
    private String creatorname;
    private String creatorgender;
    private String creatorlink;
    private int facetypeidx;
    private int personalcoloridx;
    public float eye_val;
    public int score = 0;

    public ViewEyesCreator(int creatoridx, String creatorname, String creatorgender, String creatorlink, int facetypeidx,
            int personalcoloridx) {
        this.creatoridx = creatoridx;
        this.creatorname = creatorname;
        this.creatorgender = creatorgender;
        this.creatorlink = creatorlink;
        this.facetypeidx = facetypeidx;
        this.personalcoloridx = personalcoloridx;
    }

}
