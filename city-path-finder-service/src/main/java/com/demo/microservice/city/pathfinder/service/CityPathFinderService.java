package com.demo.microservice.city.pathfinder.service;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;

/**
 * Interface declaring methods for finding connections and paths between two cities.
 * 
 * @author sofia
 * @date 2019-01-20
 */
public interface CityPathFinderService {

	/**
	 * Check if two cities are connected.
	 * 
	 * @param graph undirected graph
	 * @param origin origin city
	 * @param destination destination city
	 * @return {@link CityConnectionInfo} object
	 */
	CityConnectionInfo checkIfConnected(CityGraph graph, String origin, String destination);
	
	/**
	 * Find all paths from origin to destination.
	 * Assumes that the input graph is acyclic.
	 * 
	 * @param graph directed graph
	 * @param origin origin city
	 * @param destination destination city
	 * @return {@link DirectedCityPathInfo} object
	 */
	DirectedCityPathInfo findAllPaths(DirectedCityGraph graph, String origin, String destination);
	
	/**
	 * Find shortest paths from origin to destination.
	 * Assumes that the input graph is acyclic.
	 * 
	 * @param graph directed graph
	 * @param origin origin city
	 * @param destination destination city
	 * @return {@link DirectedCityPathInfo} object
	 */
	DirectedCityPathInfo findShortestPaths(DirectedCityGraph graph, String origin, String destination);

}
