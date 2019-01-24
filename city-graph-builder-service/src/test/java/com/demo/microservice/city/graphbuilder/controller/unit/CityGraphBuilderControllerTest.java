package com.demo.microservice.city.graphbuilder.controller.unit;

import static org.junit.Assert.assertEquals;

import java.util.SortedMap;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.graphbuilder.controller.CityGraphBuilderController;

/**
 * Unit test for {@link CityGraphBuilderController}.
 * 
 * @author sofia
 * @date 2019-01-22
 */
public class CityGraphBuilderControllerTest {

	@InjectMocks
	private CityGraphBuilderController cityGraphBuilderController;
	
	@Mock
	private CityGraph graph;
	
	@Mock
	private DirectedCityGraph directedGraph;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_getGraph() {
		CityGraph expected = graph;
		CityGraph actual = cityGraphBuilderController.getGraph();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getGraphNodeSet() {
		SortedSet<String> expected = graph.getNodeSet();
		SortedSet<String> actual = cityGraphBuilderController.getGraphNodeSet();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getGraphAdjMap() {
		SortedMap<String, SortedSet<String>> expected = graph.getAdjMap();
		SortedMap<String, SortedSet<String>> actual = cityGraphBuilderController.getGraphAdjMap();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getDirectedGraph() {
		DirectedCityGraph expected = directedGraph;
		DirectedCityGraph actual = cityGraphBuilderController.getDirectedGraph();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getDirectedGraphNodeSet() {
		SortedSet<String> expected = directedGraph.getNodeSet();
		SortedSet<String> actual = cityGraphBuilderController.getGraphNodeSet();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_getDirectedGraphAdjMap() {
		SortedMap<String, SortedSet<String>> expected = directedGraph.getAdjMap();
		SortedMap<String, SortedSet<String>> actual = cityGraphBuilderController.getGraphAdjMap();
		
		assertEquals(expected, actual);
	}

}
