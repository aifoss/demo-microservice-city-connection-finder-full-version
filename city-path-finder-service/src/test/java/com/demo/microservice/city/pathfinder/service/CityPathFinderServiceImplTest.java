package com.demo.microservice.city.pathfinder.service;

import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.DIRECTED_GRAPH;
import static com.demo.microservice.city.pathfinder.util.TestUtil.GRAPH;
import static com.demo.microservice.city.pathfinder.util.TestUtil.NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.UNKNOWN_CITY;
import static com.demo.microservice.city.pathfinder.util.TestUtil.YES_CONNECTION;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;

/**
 * Unit test for {@link CityPathFinderServiceImpl}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
public class CityPathFinderServiceImplTest {

	@InjectMocks
	private CityPathFinderServiceImpl cityPathFinderService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_checkIfConnected_yesConnection() {
		String origin = ORIGIN.toLowerCase();
		String destination = DESTINATION.toLowerCase();
		CityGraph graph = GRAPH;
		
		CityConnectionInfo expected = YES_CONNECTION;
		CityConnectionInfo actual = cityPathFinderService.checkIfConnected(graph, origin, destination);

		assertEquals(expected, actual);
	}

	@Test
	public void test_checkIfConnected_noConnection() {
		String origin = ORIGIN.toLowerCase();
		String destination = UNKNOWN_CITY.toLowerCase();
		CityGraph graph = GRAPH;
		
		CityConnectionInfo expected = NO_CONNECTION;
		CityConnectionInfo actual = cityPathFinderService.checkIfConnected(graph, origin, destination);

		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findAllPaths_yesConnection() {
		String origin = ORIGIN.toLowerCase();
		String destination = DESTINATION.toLowerCase();
		DirectedCityGraph directedGraph = DIRECTED_GRAPH;
		
		DirectedCityPathInfo expected = ALL_PATH_INFO;
		DirectedCityPathInfo actual = cityPathFinderService.findAllPaths(directedGraph, origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findAllPaths_noConnection() {
		String origin = ORIGIN.toLowerCase();
		String destination = UNKNOWN_CITY.toLowerCase();
		DirectedCityGraph directedGraph = DIRECTED_GRAPH;
		
		DirectedCityPathInfo expected = ALL_PATH_INFO_WITH_NO_CONNECTION;
		DirectedCityPathInfo actual = cityPathFinderService.findAllPaths(directedGraph, origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findShortestPaths_yesConnection() {
		String origin = ORIGIN.toLowerCase();
		String destination = DESTINATION.toLowerCase();
		DirectedCityGraph directedGraph = DIRECTED_GRAPH;
		
		DirectedCityPathInfo expected = SHORTEST_PATH_INFO;
		DirectedCityPathInfo actual = cityPathFinderService.findShortestPaths(directedGraph, origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findShortestPaths_noConnection() {
		String origin = ORIGIN.toLowerCase();
		String destination = UNKNOWN_CITY.toLowerCase();
		DirectedCityGraph directedGraph = DIRECTED_GRAPH;
		
		DirectedCityPathInfo expected = SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
		DirectedCityPathInfo actual = cityPathFinderService.findShortestPaths(directedGraph, origin, destination);
		
		assertEquals(expected, actual);
	}
	
}
