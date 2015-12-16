/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.math.BigDecimal;

/**
 *
 * @author Bert
 */
public class Node implements Comparable<Node>{
    private int index;
    private BigDecimal distance;

    public Node(int index, BigDecimal distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
    
    @Override
    public int compareTo(Node o) {
        return distance.compareTo(o.getDistance());
    }
}
