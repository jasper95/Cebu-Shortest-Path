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
    private ShortestPath shortestPath;
    
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
        //List<Edge> edges = getAllEdges();
        List<Vertex> vertices = getAllVertex();
        List<Integer>[] adjList =  new ArrayList[vertices.size()]; //adjacency list
        for(int i=0; i< adjList.length; i++){
            adjList[i] = new ArrayList();
        } 
        HashMap<Integer, Integer> indexIdMap = new HashMap(), idIndexMap = new HashMap(); 
        int src = -1, des = -1;
        int ctr=0;
        for(Vertex v : vertices){
            if(v.getId().equals(from))
                src = ctr;
            if(v.getId().equals(to))
                des = ctr;
            indexIdMap.put(v.getId(), ctr);
            idIndexMap.put(ctr, v.getId());
            ctr++;
        }
        for(Integer i: indexIdMap.keySet()){
            List<Edge> neighbors = edgeRepo.findByNodeFrom(i);
            int index = indexIdMap.get(new Integer(i));
            System.out.println(index);
            for(Edge e: neighbors){
                adjList[index].add(indexIdMap.get(e.getNodeTo()));
                if(!e.isIsOneWay())
                    adjList[indexIdMap.get(e.getNodeTo())].add(index);    
            }
        }
        return shortestPath.djisktra(adjList, new BigDecimal[vertices.size()], new HashMap(), new ArrayList(), indexIdMap.get(from), indexIdMap.get(to), idIndexMap);
    }  
}