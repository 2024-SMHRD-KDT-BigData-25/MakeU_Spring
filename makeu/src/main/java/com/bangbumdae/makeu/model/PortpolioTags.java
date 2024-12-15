package com.bangbumdae.makeu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="portpolio_tags")
public class PortpolioTags {
    @Id
    private int portfolioidx;
    private String portfolioimg;
    private int shopidx;
    private String tags;
}
