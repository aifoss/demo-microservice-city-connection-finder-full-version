package com.demo.microservice.city.common.model;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.demo.microservice.city.common.model.DirectedCityGraph;

/**
 * Unit test for {@link DirectedCityGraph}.
 * 
 * @author sofia
 * @date 2019-01-23
 */
public class DirectedCityGraphTest {

	@Test
	public void test() {
		DirectedCityGraph graph1 = DIRECTED_GRAPH.clone();
		DirectedCityGraph graph2 = DIRECTED_GRAPH.clone();
		
		assertEquals(graph1, graph2);
		assertEquals(graph1.toString(), graph2.toString());
		
		assertEquals(5, graph1.getNodeSet().size());
		assertEquals(3, graph1.getAdjMap().get("philadelphia").size());
		assertTrue(graph1.getAdjMap().get("philadelphia").contains("new york"));
		assertTrue(graph1.getAdjMap().get("philadelphia").contains("boston"));
		assertTrue(graph1.getAdjMap().get("philadelphia").contains("washington dc"));
		assertFalse(graph1.getAdjMap().containsKey("new york"));
	}

}
