/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.impl;

import com.dao.springdatajpa.EdgeRepository;
import com.dao.springdatajpa.VertexRepository;
import com.domain.Edge;
import com.domain.Vertex;
import com.json_response.PathAndCost;
import com.service.DataService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bert
 */
@Service
public class DataServiceImpl implements DataService{
    @Autowired
    private EdgeRepository edgeRepo;
    @Autowired
    private VertexRepository vertexRepo;
    @Autowired
    private ShortestPath sp;
    
    @Transactional(readOnly=true)
    @Override
    public List<Vertex> getAllVertex() {
        return vertexRepo.findAll();
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<Edge> getAllEdges() {
        return edgeRepo.findAll();
    }
    
    @Transactional(readOnly=true)
    @Override
    public PathAndCost getShortestPath(Integer from, Integer to) {
        List<Edge> edges = getAllEdges();
        List<Vertex> vertices = getAllVertex();
        ArrayList<Integer>[] adjList =  new ArrayList[vertices.size()]; //adjacency list
        HashMap<Integer, Integer> indexIdMap = new HashMap(); //map for index(key), value id 
        ArrayList<Integer> vertexIds = new ArrayList(); //checker if vertex id is in the map
        int src = -1, des = -1;
        for(int i=0; i < edges.size(); i++){
            if(edges.get((i)).getNodeFrom().equals(from) && src == -1)
                src = edges.get((i)).getNodeFrom();
            if(edges.get((i)).getNodeTo().equals(from) && src == -1)
                src = edges.get((i)).getNodeTo();
            if(edges.get(i).getNodeTo().equals(to) && des == -1)
                des = edges.get(i).getNodeTo();
            if(edges.get((i)).getNodeFrom().equals(to) && des == -1)
                des = edges.get((i)).getNodeFrom(); 
            if(!vertexIds.contains(edges.get((i)).getNodeFrom()-1)){
                vertexIds.add(edges.get((i)).getNodeFrom()-1);
                indexIdMap.put(i, edges.get((i)).getNodeFrom()-1);
            }
            adjList[edges.get((i)).getNodeFrom()-1].add(edges.get(i).getNodeTo()-1);
        }
        return sp.djisktra(adjList, new BigDecimal[vertices.size()], new ArrayList(), new HashMap(), new ArrayList(), indexIdMap.get(from), indexIdMap.get(to), indexIdMap);
    }  
}