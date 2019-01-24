package com.demo.microservice.city.common.model;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.demo.microservice.city.common.model.CityGraph;

/**
 * Unit test for {@link CityGraph}.
 * 
 * @author sofia
 * @date 2019-01-23
 */
public class CityGraphTest {

	@Test
	public void test() {
		CityGraph graph1 = GRAPH.clone();
		CityGraph graph2 = GRAPH.clone();
		
		assertEquals(graph1, graph2);
		assertEquals(graph1.toString(), graph2.toString());
		
		assertEquals(5, graph1.getNodeSet().size());
		assertEquals(3, graph1.getAdjMap().get("philadelphia").size());
		assertTrue(graph1.getAdjMap().get("philadelphia").contains("new york"));
		assertTrue(graph1.getAdjMap().get("philadelphia").contains("boston"));
		assertTrue(graph1.getAdjMap().get("philadelphia").contains("washington dc"));
		assertEquals(3, graph1.getAdjMap().get("new york").size());
		assertTrue(graph1.getAdjMap().get("new york").contains("philadelphia"));
		assertTrue(graph1.getAdjMap().get("new york").contains("boston"));
		assertTrue(graph1.getAdjMap().get("new york").contains("seattle"));
	}

}
