/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bert
 */
@Entity
@Table(name="vertex"
    ,catalog="142projectdb"
)
public class Vertex {
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    @Column(name="lat", nullable=false, precision=59, scale=0)
    private BigDecimal lat;
    @Column(name="lng", nullable=false, precision=59, scale=0)
    private BigDecimal lng;
    @Column(name="is_land_mark", nullable=false)
    private boolean isLandmark;
    @Column(name="name", nullable=false, length=100)
    private String name;

    public Vertex(BigDecimal lat, BigDecimal lng, boolean isLandmark, String name) {
        this.lat = lat;
        this.lng = lng;
        this.isLandmark = isLandmark;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public boolean isIsLandmark() {
        return isLandmark;
    }

    public void setIsLandmark(boolean isLandmark) {
        this.isLandmark = isLandmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
