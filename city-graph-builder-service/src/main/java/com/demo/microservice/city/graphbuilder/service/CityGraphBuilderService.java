package com.demo.microservice.city.graphbuilder.service;

import java.io.IOException;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;

/**
 * Interface declaring methods for building {@link CityGraph} and {@link DirectedCityGraph}.
 * 
 * @author sofia
 * @date 2019-01-20
 */
public interface CityGraphBuilderService {
	
	/**
	 * Builds an undirected graph with cities as nodes and two-way connections between cities as edges.
	 * 
	 * @return {@link CityGraph} object 
	 * @throws IOException
	 */
	CityGraph buildGraph() throws IOException; 

	/**
	 * Builds a directed graph with cities as nodes and directed paths from one city to another as edges.
	 * 
	 * @return {@link DirectedCityGraph} object
	 * @throws IOException
	 */
	DirectedCityGraph buildDirectedGraph() throws IOException;
	
}
