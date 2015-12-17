/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.json_response.PathAndCost;
import org.springframework.web.bind.annotation.RestController;
import com.service.DataService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Bert
 */

@RestController
public class IndexRestController {
    
    @Autowired
    private DataService dataService;
    
    @RequestMapping(value="/get-all-vertices-edges")
    public ResponseEntity<HashMap> getAllVerticesAndEdges(){
        HashMap<String, Object> response= new HashMap();
        response.put("vertices", dataService.getAllVertex());
        response.put("edges", dataService.getAllEdges());
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
    }
    
    @RequestMapping("/find-shortest-path/{idFrom}/{idTo}")
    public ResponseEntity<PathAndCost> shortestPath(@PathVariable("idFrom")Integer idFrom, @PathVariable("idTo")Integer idTo){
        return new ResponseEntity(dataService.getShortestPath(idFrom, idTo), HttpStatus.OK);
    }
}