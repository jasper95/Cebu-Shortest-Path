/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json_response;

import com.domain.Vertex;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Bert
 */
public class PathAndCost implements java.io.Serializable {
    private List<Vertex> path;
    private BigDecimal cost;

    public PathAndCost(List<Vertex> path, BigDecimal cost) {
        this.path = path;
        this.cost = cost;
    }

    public List<Vertex> getPath() {
        return path;
    }

    public void setPath(List<Vertex> path) {
        this.path = path;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }    
}
