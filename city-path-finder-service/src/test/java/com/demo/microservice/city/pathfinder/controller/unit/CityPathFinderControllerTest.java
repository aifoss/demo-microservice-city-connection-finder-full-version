package com.demo.microservice.city.pathfinder.controller.unit;

import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.UNKNOWN_CITY;
import static com.demo.microservice.city.pathfinder.util.TestUtil.YES_CONNECTION;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.controller.CityPathFinderController;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;
import com.demo.microservice.city.pathfinder.proxy.CityGraphBuilderServiceProxy;
import com.demo.microservice.city.pathfinder.service.CityPathFinderService;

/**
 * Unit test for {@link CityPathFinderController}.
 *
 * @author sofia
 * @date 2019-01-21
 */

public class CityPathFinderControllerTest {

	@InjectMocks
	private CityPathFinderController cityPathFinderController;
	
	@Mock
	private CityGraphBuilderServiceProxy cityGraphBuilderService;
	
	@Mock
	private CityPathFinderService cityPathFinderService;
	
	@Mock
	private CityGraph graph;
	
	@Mock
	private DirectedCityGraph directedGraph;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_checkIfConnected_yesConnection() {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		when(cityGraphBuilderService.getGraph()).thenReturn(graph);
		when(cityPathFinderService.checkIfConnected(
				graph, origin.toLowerCase(), destination.toLowerCase())).thenReturn(YES_CONNECTION);
		
		CityConnectionInfo expected = YES_CONNECTION;
		CityConnectionInfo actual = cityPathFinderController.checkIfConnected(origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_checkIfConnected_noConnection() {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		when(cityGraphBuilderService.getGraph()).thenReturn(graph);
		when(cityPathFinderService.checkIfConnected(
				graph, origin.toLowerCase(), destination.toLowerCase())).thenReturn(NO_CONNECTION);
		
		CityConnectionInfo expected = NO_CONNECTION;
		CityConnectionInfo actual = cityPathFinderController.checkIfConnected(origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findAllPaths_yesConnection() {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		when(cityGraphBuilderService.getDirectedGraph()).thenReturn(directedGraph);
		when(cityPathFinderService.findAllPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).thenReturn(ALL_PATH_INFO);
		
		DirectedCityPathInfo expected = ALL_PATH_INFO;
		DirectedCityPathInfo actual = cityPathFinderController.findAllPaths(origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findAllPaths_noConnection() {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		when(cityGraphBuilderService.getDirectedGraph()).thenReturn(directedGraph);
		when(cityPathFinderService.findAllPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).thenReturn(ALL_PATH_INFO_WITH_NO_CONNECTION);
		
		DirectedCityPathInfo expected = ALL_PATH_INFO_WITH_NO_CONNECTION;
		DirectedCityPathInfo actual = cityPathFinderController.findAllPaths(origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findShortestPaths_yesConnection() {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		when(cityGraphBuilderService.getDirectedGraph()).thenReturn(directedGraph);
		when(cityPathFinderService.findShortestPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).thenReturn(SHORTEST_PATH_INFO);
		
		DirectedCityPathInfo expected = SHORTEST_PATH_INFO;
		DirectedCityPathInfo actual = cityPathFinderController.findShortestPaths(origin, destination);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_findShortestPaths_noConnection() {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		when(cityGraphBuilderService.getDirectedGraph()).thenReturn(directedGraph);
		when(cityPathFinderService.findShortestPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).thenReturn(SHORTEST_PATH_INFO_WITH_NO_CONNECTION);
		
		DirectedCityPathInfo expected = SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
		DirectedCityPathInfo actual = cityPathFinderController.findShortestPaths(origin, destination);
		
		assertEquals(expected, actual);
	}
	
}
