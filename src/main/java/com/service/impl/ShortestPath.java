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
import com.util.Node;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bert
 */
@Service("shortestPath")
public class ShortestPath {
    private static final BigDecimal INF = new BigDecimal("11111111111111.00");
    @Autowired
    private EdgeRepository edgeRepo;
    @Autowired
    private VertexRepository vertexRepo;
    public PathAndCost djisktra(List<Integer>[] adjList, BigDecimal[] distance,  HashMap<Integer, Integer> predecessor, ArrayList<Integer> visited, int src, int des, HashMap<Integer, Integer> idIndexMap){
        if(src == des){
            ArrayList<Vertex> path = new ArrayList();
            Integer pred = des;
            while(pred != null){
                Vertex v = vertexRepo.findOne(idIndexMap.get(pred));
                path.add(v);
                pred = predecessor.get(pred);
            }
            return new PathAndCost(path, distance[des]);
        } else {
            if(visited.isEmpty()){
                Arrays.fill(distance, INF);
                distance[src] = BigDecimal.ZERO;
            }
            for(int n: adjList[src]){
                if(!visited.contains(n)){
                    List<Edge> edgeResult = edgeRepo.findByNodeFromAndNodeTo(idIndexMap.get(new Integer(src)), idIndexMap.get(new Integer(n)));
                    BigDecimal newDistance;
                    if(edgeResult.isEmpty()){
                        edgeResult = edgeRepo.findByNodeFromAndNodeTo(idIndexMap.get(new Integer(n)), idIndexMap.get(new Integer(src)));
                        newDistance = (!edgeResult.isEmpty()) ? edgeResult.get(0).getDistance() : INF;
                    } else{
                        newDistance = edgeResult.get(0).getDistance();
                    }                
                    if(distance[n].compareTo(newDistance.add(distance[src])) > 0){
                        distance[n] = newDistance.add(distance[src]);
                        predecessor.put(n, src);
                    }
                }
            }
            visited.add(src);
            PriorityQueue<Node> unvisited = new PriorityQueue(); 
            for(int i = 0 ; i < adjList.length; i++){
                if(!visited.contains(i))
                    unvisited.add(new Node(i, distance[i]));
            }
            return djisktra(adjList, distance, predecessor, visited, unvisited.poll().getIndex(), des, idIndexMap);
        }            
    }
}
