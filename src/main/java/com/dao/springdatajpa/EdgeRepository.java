/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.springdatajpa;

import com.domain.Edge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Bert
 */
public interface EdgeRepository extends JpaRepository<Edge, Integer>{
    public List<Edge> findByNodeFromAndNodeTo(Integer nodeFrom, Integer nodeTo);
    public List<Edge> findByNodeFrom(Integer nodeFrom);
}
