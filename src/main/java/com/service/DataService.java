/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.domain.Edge;
import com.domain.Vertex;
import com.json_response.PathAndCost;
import java.util.List;

/**
 *
 * @author Bert
 */
public interface DataService {
    public List<Vertex> getAllVertex();
    public List<Edge> getAllEdges();  
    public PathAndCost getShortestPath(Integer from, Integer to);
    
}
