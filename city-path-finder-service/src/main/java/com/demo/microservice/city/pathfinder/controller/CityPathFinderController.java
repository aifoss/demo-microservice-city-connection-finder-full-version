package com.demo.microservice.city.pathfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;
import com.demo.microservice.city.pathfinder.proxy.CityGraphBuilderServiceProxy;
import com.demo.microservice.city.pathfinder.service.CityPathFinderService;

import io.swagger.annotations.ApiOperation;

/**
 * Rest controller for city-path-finder-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@RestController
@RequestMapping("/path-finder")
public class CityPathFinderController {
	
	private static final String RESPONSE_FORMAT = "application/json";
	
	@Autowired
	private CityGraphBuilderServiceProxy cityGraphBuilderService;
	
	@Autowired
	private CityPathFinderService cityPathFinderService;

	@ApiOperation(
			value = "Check if two cities are connected", 
			response = CityConnectionInfo.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/connected/origin/{origin}/destination/{destination}")
	public CityConnectionInfo checkIfConnected(
			@PathVariable String origin,
			@PathVariable String destination) {
		
		CityGraph graph = cityGraphBuilderService.getGraph();
		
		CityConnectionInfo result = cityPathFinderService.checkIfConnected(graph, origin.toLowerCase(), destination.toLowerCase());
		
		return result;
	}

	@ApiOperation(
			value = "Get all paths from origin to destination", 
			response = DirectedCityPathInfo.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/paths-all/origin/{origin}/destination/{destination}") 
	public DirectedCityPathInfo findAllPaths(
			@PathVariable String origin,
			@PathVariable String destination) {
		
		DirectedCityGraph directedGraph = cityGraphBuilderService.getDirectedGraph();
		
		DirectedCityPathInfo result = cityPathFinderService.findAllPaths(directedGraph, origin.toLowerCase(), destination.toLowerCase());
		
		return result;
	}
	
	@ApiOperation(
			value = "Get shortest paths from origin to destination", 
			response = DirectedCityPathInfo.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/paths-shortest/origin/{origin}/destination/{destination}")
	public DirectedCityPathInfo findShortestPaths(
			@PathVariable String origin,
			@PathVariable String destination) {
		
		DirectedCityGraph directedGraph = cityGraphBuilderService.getDirectedGraph();
		
		DirectedCityPathInfo result = cityPathFinderService.findShortestPaths(directedGraph, origin.toLowerCase(), destination.toLowerCase());
		
		return result;
	}
	
}
