/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.impl;

import com.dao.springdatajpa.EdgeRepository;
import com.dao.springdatajpa.VertexRepository;
import com.domain.Vertex;
import com.json_response.PathAndCost;
import com.util.Node;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bert
 */
@Service
public class ShortestPath {
    private static final BigDecimal INF = new BigDecimal("1111111111111111111111111111111111l");
    @Autowired
    private EdgeRepository edgeRepo;
    @Autowired
    private VertexRepository vertexRepo;
    public PathAndCost djisktra(ArrayList<Integer>[] adjList, BigDecimal[] distance, ArrayList<Vertex> path, HashMap<Integer, Integer> predecessor, ArrayList<Integer> visited, int src, int des, HashMap<Integer, Integer> indexIdMap){
        if(src == des){
            Integer pred = des;
            while(pred != null){
                Vertex v = vertexRepo.findOne(indexIdMap.get(pred+1));
                path.add(v);
                pred = predecessor.get(pred);
            }
            return new PathAndCost(path, distance[des]);
        } else {
            if(visited.isEmpty()){
                distance[src] = BigDecimal.ZERO;
                Arrays.fill(distance, INF);
            }
            for(int n: adjList[src]){
                BigDecimal newDistance = edgeRepo.findByNodeFromAndNodeTo(indexIdMap.get(src+1), indexIdMap.get(n+1)).get(0).getDistance();
                if(distance[n].compareTo(newDistance) < 0){
                    distance[n] = newDistance;
                    predecessor.put(n, src);
                }
            }
            visited.add(src);
            PriorityQueue<Node> unvisited = new PriorityQueue(); 
            for(int i = 0 ; i < adjList.length; i++){
                if(!visited.contains(i))
                    unvisited.add(new Node(i, distance[i]));
            }
            return djisktra(adjList, distance, path, predecessor, visited, unvisited.poll().getIndex(), des, indexIdMap);
        }            
    }
}
