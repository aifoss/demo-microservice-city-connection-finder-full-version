package com.demo.microservice.city.graphbuilder.service;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;

/**
 * Unit test for {@link CityGraphBuilderServiceImpl}.
 * 
 * @author sofia
 * @date 2019-01-23
 */
public class CityGraphBuilderServiceImplTest {

	@InjectMocks
	private CityGraphBuilderServiceImpl cityGraphBuilderService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(cityGraphBuilderService, "inputFilePath", "/static/test-input.txt");
	}
	
	@Test
	public void test_buildGraph() throws IOException {
		CityGraph expected = GRAPH;
		CityGraph actual = cityGraphBuilderService.buildGraph();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_buildDirectedGraph() throws IOException {
		DirectedCityGraph expected = DIRECTED_GRAPH;
		DirectedCityGraph actual = cityGraphBuilderService.buildDirectedGraph();
		
		assertEquals(expected, actual);
	}
	
}
