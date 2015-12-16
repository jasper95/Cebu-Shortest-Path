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
@Table(name="edge"
    ,catalog="142projectdb"
)
public class Edge implements java.io.Serializable{
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    @Column(name="node_from", nullable=false)
    private Integer nodeFrom;
    @Column(name="node_to", nullable=false)
    private Integer nodeTo;
    @Column(name="is_one_way", nullable=false)
    private boolean isOneWay;
    @Column(name="traffic_level", nullable=false)
    private int trafficLevel;
    @Column(name="distance", nullable=false, precision=11)
    private BigDecimal distance;
    
    public Edge(Integer nodeFrom, Integer nodeTo, boolean isOneWay, int trafficLevel) {
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
        this.isOneWay = isOneWay;
        this.trafficLevel = trafficLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodeFrom() {
        return nodeFrom;
    }

    public void setNodeFrom(Integer nodeFrom) {
        this.nodeFrom = nodeFrom;
    }

    public Integer getNodeTo() {
        return nodeTo;
    }

    public void setNodeTo(Integer nodeTo) {
        this.nodeTo = nodeTo;
    }

    public boolean isIsOneWay() {
        return isOneWay;
    }

    public void setIsOneWay(boolean isOneWay) {
        this.isOneWay = isOneWay;
    }

    public int getTrafficLevel() {
        return trafficLevel;
    }

    public void setTrafficLevel(int trafficLevel) {
        this.trafficLevel = trafficLevel;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
