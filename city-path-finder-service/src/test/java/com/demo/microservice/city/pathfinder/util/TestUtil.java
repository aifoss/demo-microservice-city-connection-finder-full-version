package com.demo.microservice.city.pathfinder.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo.Connected;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;

/**
 * Utility for testing.
 * 
 * @author sofia
 * @date 2019-01-21
 */
public class TestUtil {
	
	public static final CityGraph GRAPH = graph();
	public static final DirectedCityGraph DIRECTED_GRAPH = directedGraph();

	public static final String ORIGIN = "Philadelphia";
	public static final String DESTINATION = "New York";
	public static final String UNKNOWN_CITY = "Some Other City";
	
	public static final CityConnectionInfo YES_CONNECTION = yesConnection();
	public static final CityConnectionInfo NO_CONNECTION = noConnection();
	public static final CityConnectionInfo UNKNOWN_CONNECTION = unknownConnection();
	
	public static final DirectedCityPathInfo ALL_PATH_INFO = allPathInfo();
	public static final DirectedCityPathInfo ALL_PATH_INFO_WITH_NO_CONNECTION = allPathInfoWithNoConnection();
	
	public static final DirectedCityPathInfo SHORTEST_PATH_INFO = shortestPathInfo();
	public static final DirectedCityPathInfo SHORTEST_PATH_INFO_WITH_NO_CONNECTION = shortestPathInfoWithNoConnection();
	
	public static final String YES_CONNECTION_STRING = YES_CONNECTION.toString();
	public static final String NO_CONNECTION_STRING = NO_CONNECTION.toString();
	public static final String UNKNOWN_CONNECTION_STRING = UNKNOWN_CONNECTION.toString();
	
	public static final String ALL_PATH_INFO_STRING = ALL_PATH_INFO.toString();
	public static final String ALL_PATH_INFO_WITH_NO_CONNECTION_STRING = ALL_PATH_INFO_WITH_NO_CONNECTION.toString();
	
	public static final String SHORTEST_PATH_INFO_STRING = SHORTEST_PATH_INFO.toString();
	public static final String SHORTEST_PATH_INFO_WITH_NO_CONNECTION_STRING = SHORTEST_PATH_INFO_WITH_NO_CONNECTION.toString();

	public static CityConnectionInfo yesConnection() {
		return new CityConnectionInfo(ORIGIN.toLowerCase(), DESTINATION.toLowerCase(), Connected.YES);
	}
	
	public static CityConnectionInfo noConnection() {
		return new CityConnectionInfo(ORIGIN.toLowerCase(), UNKNOWN_CITY.toLowerCase(), Connected.NO);
	}
	
	public static CityConnectionInfo unknownConnection() {
		return new CityConnectionInfo(ORIGIN.toLowerCase(), DESTINATION.toLowerCase(), Connected.UNKNOWN);
	}
	
	public static DirectedCityPathInfo allPathInfo() {
		DirectedCityPathInfo result = new DirectedCityPathInfo(ORIGIN.toLowerCase(), DESTINATION.toLowerCase(), Connected.YES);
		
		List<List<String>> paths = new ArrayList<>();
		paths.add(Arrays.asList("philadelphia", "new york"));
		paths.add(Arrays.asList("philadelphia", "boston", "new york"));
		paths.add(Arrays.asList("philadelphia", "washington dc", "seattle", "new york"));
		
		result.setNumPaths(3);
		result.setShortestPathSize(2);
		result.setLongestPathSize(4);
		result.setPaths(paths);
		
		return result;
	}
	
	public static DirectedCityPathInfo allPathInfoWithNoConnection() {
		return new DirectedCityPathInfo(ORIGIN.toLowerCase(), UNKNOWN_CITY.toLowerCase(), Connected.NO);
	}
	
	public static DirectedCityPathInfo shortestPathInfo() {
		DirectedCityPathInfo result = new DirectedCityPathInfo(ORIGIN.toLowerCase(), DESTINATION.toLowerCase(), Connected.YES);
		
		result.setNumPaths(1);
		result.setShortestPathSize(2);
		result.setLongestPathSize(-1);
		result.addPath(Arrays.asList("philadelphia", "new york"));
		
		return result;
	}
	
	public static DirectedCityPathInfo shortestPathInfoWithNoConnection() {
		return new DirectedCityPathInfo(ORIGIN.toLowerCase(), UNKNOWN_CITY.toLowerCase(), Connected.NO);
	}
	
	public static String getConnectedApiUri(String origin, String destination) {
		return "/path-finder/connected/origin/" + origin + "/destination/" + destination;
	}
	
	public static String getFindAllPathsApiUri(String origin, String destination) {
		return "/path-finder/paths-all/origin/" + origin + "/destination/" + destination;
	}

	public static String getFindShortestPathsApiUri(String origin, String destination) {
		return "/path-finder/paths-shortest/origin/" + origin + "/destination/" + destination;
	}
	
	public static String getHystrixConnectedApiUri(String origin, String destination) {
		return "/hystrix/connected/origin/" + origin + "/destination/" + destination;
	}
	
	public static String getHystrixFindAllPathsApiUri(String origin, String destination) {
		return "/hystrix/paths-all/origin/" + origin + "/destination/" + destination;
	}

	public static String getHystrixFindShortestPathsApiUri(String origin, String destination) {
		return "/hystrix/paths-shortest/origin/" + origin + "/destination/" + destination;
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
