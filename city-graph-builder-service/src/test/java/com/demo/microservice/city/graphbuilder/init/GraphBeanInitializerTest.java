package com.demo.microservice.city.graphbuilder.init;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.graphbuilder.service.CityGraphBuilderService;

/**
 * Unit test for {@link GraphBeanInitializer}.
 * 
 * @author sofia
 * @date 2019-01-22
 */
public class GraphBeanInitializerTest {

	@InjectMocks
	private GraphBeanInitializer graphBeanInitializer;
	
	@Mock
	private CityGraphBuilderService cityGraphBuilderService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_graph() throws IOException {
		when(cityGraphBuilderService.buildGraph()).thenReturn(GRAPH);
		
		CityGraph graph = graphBeanInitializer.graph();
		
		assertEquals(GRAPH, graph);
	}
	
	@Test
	public void test_directedGraph() throws IOException {
		when(cityGraphBuilderService.buildDirectedGraph()).thenReturn(DIRECTED_GRAPH);
		
		DirectedCityGraph directedGraph = graphBeanInitializer.directedGraph();
		
		assertEquals(DIRECTED_GRAPH, directedGraph);
	}
	
}
