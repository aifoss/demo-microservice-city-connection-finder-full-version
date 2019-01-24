package com.demo.microservice.city.graphbuilder.util;

import java.util.SortedMap;
import java.util.SortedSet;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;

/**
 * Utility for testing.
 * 
 * @author sofia
 * @date 2019-01-22
 */
public class TestUtil {
	
	public static final CityGraph GRAPH = graph();
	public static final SortedSet<String> GRAPH_NODE_SET = GRAPH.getNodeSet();
	public static final SortedMap<String, SortedSet<String>> GRAPH_ADJ_MAP = GRAPH.getAdjMap();
	
	public static final String GRAPH_STRING = GRAPH.toString();
	public static final String GRAPH_NODE_SET_STRING = GRAPH.nodeSetToString();
	public static final String GRAPH_ADJ_MAP_STRING = GRAPH.adjMapToString();
	
	public static final DirectedCityGraph DIRECTED_GRAPH = directedGraph();
	public static final SortedSet<String> DIRECTED_GRAPH_NODE_SET = DIRECTED_GRAPH.getNodeSet();
	public static final SortedMap<String, SortedSet<String>> DIRECTED_GRAPH_ADJ_MAP = DIRECTED_GRAPH.getAdjMap();
	
	public static final String DIRECTED_GRAPH_STRING = DIRECTED_GRAPH.toString();
	public static final String DIRECTED_GRAPH_NODE_SET_STRING = DIRECTED_GRAPH.nodeSetToString();
	public static final String DIRECTED_GRAPH_ADJ_MAP_STRING = DIRECTED_GRAPH.adjMapToString();
	
	public static final String getGraphApiUri() {
		return "/graph-builder/graph";
	}
	
	public static final String getGraphNodeSetApiUri() {
		return "/graph-builder/graph/nodes";
	}
	
	public static final String getGraphAdjMapApiUri() {
		return "/graph-builder/graph/adj";
	}
	
	public static final String getDirectedGraphApiUri() {
		return "/graph-builder/directed-graph";
	}
	
	public static final String getDirectedGraphNodeSetApiUri() {
		return "/graph-builder/directed-graph/nodes";
	}
	
	public static final String getDirectedGraphAdjMapApiUri() {
		return "/graph-builder/directed-graph/adj";
	}

	public static CityGraph graph() {
		CityGraph graph = new CityGraph();
		addEdges(graph);
		return graph;
	}
	
	public static DirectedCityGraph directedGraph() {
		DirectedCityGraph directedGraph = new DirectedCityGraph();
		addEdges(directedGraph);
		return directedGraph;
	}
	
	private static void addEdges(CityGraph graph) {
		graph.addEdge("philadelphia", "new york");
		graph.addEdge("philadelphia", "boston");
		graph.addEdge("philadelphia", "washington dc");
		graph.addEdge("boston", "new york");
		graph.addEdge("washington dc", "seattle");
		graph.addEdge("seattle", "new york");
	}
	
}
