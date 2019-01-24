package com.demo.microservice.city.common.model;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Data model to represent a graph where a node represents a city
 * and an unweighted edge represents an undirected two-way connection between two cities.
 * 
 * @author sofia
 * @date 2019-01-20
 */
public class CityGraph implements Cloneable {

	protected SortedSet<String> nodeSet;
	protected SortedMap<String, SortedSet<String>> adjMap;

	public CityGraph() {
		nodeSet = new TreeSet<>();
		adjMap = new TreeMap<>();
	}
    
	public void addEdge(String v, String w) {
		nodeSet.add(v);
		nodeSet.add(w);

		adjMap.computeIfAbsent(v, k -> new TreeSet<>()).add(w);
		adjMap.computeIfAbsent(w, k -> new TreeSet<>()).add(v);
	}
	
	@Override
	public CityGraph clone() {
		CityGraph newGraph = new CityGraph();
		
		SortedSet<String> newNodeSet = new TreeSet<>(nodeSet);
		SortedMap<String, SortedSet<String>> newAdjMap = new TreeMap<>();
		
		for (Map.Entry<String, SortedSet<String>> entry : adjMap.entrySet()) {
			newAdjMap.put(entry.getKey(), new TreeSet<>(entry.getValue()));
		}
		
		newGraph.setNodeSet(newNodeSet);
		newGraph.setAdjMap(newAdjMap);
		
		return newGraph;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o.getClass() != this.getClass()) return false;
		
		CityGraph other = (CityGraph) o;
		
		return this.nodeSet.equals(other.nodeSet)
				&& this.adjMap.equals(other.adjMap);
	}

	@Override
	public int hashCode() {
		int hash = 31;
		
		hash = (hash * 17) + nodeSet.hashCode();
		hash = (hash * 17) + adjMap.hashCode();
		
		return hash;
	}
	    
    public SortedSet<String> getNodeSet() {
		return nodeSet;
	}

	public void setNodeSet(SortedSet<String> nodeSet) {
		this.nodeSet = nodeSet;
	}

	public SortedMap<String, SortedSet<String>> getAdjMap() {
		return adjMap;
	}

	public void setAdjMap(SortedMap<String, SortedSet<String>> adjMap) {
		this.adjMap = adjMap;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

    public String nodeSetToString() {
    	try {
    		return new ObjectMapper().writeValueAsString(nodeSet);
    	} catch (Exception e) {
    		return nodeSet.toString();
    	}
    }
    
    public String adjMapToString() {
    	try {
    		return new ObjectMapper().writeValueAsString(adjMap);
    	} catch (Exception e) {
    		return adjMap.toString();
    	}
    }
    
}
