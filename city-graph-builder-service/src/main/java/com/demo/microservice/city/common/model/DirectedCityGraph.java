package com.demo.microservice.city.common.model;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Data model to represent a graph where a node represents a city
 * and an unweighted edge represents a directed path from one city to another.
 * 
 * @author sofia
 * @date 2019-01-20
 */
public class DirectedCityGraph extends CityGraph {
	
	public DirectedCityGraph() {
		super();
	}
    
    @Override
	public void addEdge(String v, String w) {
        nodeSet.add(v);
        nodeSet.add(w);
        
        adjMap.computeIfAbsent(v, k -> new TreeSet<>()).add(w);
    }
    
    @Override
    public DirectedCityGraph clone() {
    	DirectedCityGraph newDirectedGraph = new DirectedCityGraph();
    	
    	SortedSet<String> newNodeSet = new TreeSet<>(nodeSet);
		SortedMap<String, SortedSet<String>> newAdjMap = new TreeMap<>();
		
		for (Map.Entry<String, SortedSet<String>> entry : adjMap.entrySet()) {
			newAdjMap.put(entry.getKey(), new TreeSet<>(entry.getValue()));
		}
    	
		newDirectedGraph.setNodeSet(newNodeSet);
		newDirectedGraph.setAdjMap(newAdjMap);
		
    	return newDirectedGraph;
    }
    
}
