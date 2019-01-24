package com.demo.microservice.city.graphbuilder.controller;

import java.util.SortedMap;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;

import io.swagger.annotations.ApiOperation;

/**
 * Rest controller for city-graph-builder-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@RestController
@RequestMapping("/graph-builder")
public class CityGraphBuilderController {

	private static final String RESPONSE_FORMAT = "application/json";
	
	@Autowired
	@Qualifier("graph")
	protected CityGraph graph;
	
	@Autowired
	@Qualifier("directedGraph")
	protected DirectedCityGraph directedGraph;
	
	/* Undirected Graph */
	
	@ApiOperation(
			value = "Get the graph representing undirected city connections", 
			response = CityGraph.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/graph")
	public CityGraph getGraph() {
		return graph;
	}
	
	@ApiOperation(
			value = "Get the node set of the graph representing undirected city connections", 
			response = SortedSet.class,
			produces = RESPONSE_FORMAT)
	@GetMapping("/graph/nodes")
	public SortedSet<String> getGraphNodeSet() {
		return graph.getNodeSet();
	}
	
	@ApiOperation(
			value = "Get the adjacency map of the graph representing undirected city connections", 
			response = SortedMap.class,
			produces = RESPONSE_FORMAT)
	@GetMapping("/graph/adj")
	public SortedMap<String, SortedSet<String>> getGraphAdjMap() {
		return graph.getAdjMap();
	}
	
	/* Directed Graph */
	
	@ApiOperation(
			value = "Get the graph representing directed paths between cities", 
			response = DirectedCityGraph.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/directed-graph")
	public DirectedCityGraph getDirectedGraph() {
		return directedGraph;
	}
	
	@ApiOperation(
			value = "Get the node set of the graph representing directed paths between cities", 
			response = SortedSet.class,
			produces = RESPONSE_FORMAT)
	@GetMapping("/directed-graph/nodes")
	public SortedSet<String> getDirectedGraphNodeSet() {
		return directedGraph.getNodeSet();
	}
	
	@ApiOperation(
			value = "Get the adjacency map of the graph representing directed paths between cities", 
			response = SortedMap.class,
			produces = RESPONSE_FORMAT)
	@GetMapping("/directed-graph/adj")
	public SortedMap<String, SortedSet<String>> getDirectedGraphAdjMap() {
		return directedGraph.getAdjMap();
	}
	
}
