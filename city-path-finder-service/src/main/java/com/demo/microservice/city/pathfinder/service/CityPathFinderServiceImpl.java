package com.demo.microservice.city.pathfinder.service;

import static com.demo.microservice.city.pathfinder.model.CityConnectionInfo.Connected.NO;
import static com.demo.microservice.city.pathfinder.model.CityConnectionInfo.Connected.YES;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.config.HazelcastConfig;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;

/**
 * Implementation of {@link CityPathFinderService}.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@Service
public class CityPathFinderServiceImpl implements CityPathFinderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityPathFinderServiceImpl.class);
	
	@Override
	@Cacheable(HazelcastConfig.CITY_CONNECTION_CACHE_NAME)
	public CityConnectionInfo checkIfConnected(CityGraph graph, String origin, String destination) {
		LOGGER.info("Checking if connection exists between {{}} and {{}}", origin, destination);
		
		CityConnectionInfo result = new CityConnectionInfo(origin, destination);
		
		if (origin.equals(destination)) {
        	LOGGER.info("Identical city {{}} for origin and destination, returning positive response", origin);
        	result.setConnected(YES);
        	return result;
        }
		
		if (!graph.getNodeSet().contains(origin)) {
			LOGGER.info("Unrecognized origin: {{}}, returning negative response", origin);
			result.setConnected(NO);
			return result;
		}
		if (!graph.getNodeSet().contains(destination)) {
			LOGGER.info("Unrecognized destination: {{}}, returning negative response", destination);
			result.setConnected(NO);
			return result;
		}
		
		if (graph.getAdjMap().get(origin).contains(destination)) {
			LOGGER.info("Diret connection exists, returning positive response");
			result.setConnected(YES);
			return result;
		}
        
		return checkIfIndirectlyConnected(graph, origin, destination, result);
	}
		
	@Override
	@Cacheable(HazelcastConfig.ALL_PATH_CACHE_NAME)
	public DirectedCityPathInfo findAllPaths(DirectedCityGraph graph, String origin, String destination) {
		LOGGER.info("Searching for all paths from {{}} to {{}}", origin, destination);
		
		DirectedCityPathInfo result = new DirectedCityPathInfo(origin, destination);
		
		Set<String> visited = new HashSet<>();
		List<String> path = new ArrayList<>();
		
		path.add(origin);
		
		findAllPaths(graph, origin, destination, visited, path, result);
		
		if (result.getPaths().isEmpty()) {
			LOGGER.info("No path found");
			result.setConnected(NO);
			
		} else {
			LOGGER.info("{} path(s) found", result.getPaths().size());
			
			List<List<String>> sortedPaths = result.getPaths().stream()
					.sorted(Comparator.comparing(List::size))
					.collect(Collectors.toList());
			
			List<String> shortestPath = sortedPaths.get(0);
			List<String> longestPath = sortedPaths.get(sortedPaths.size()-1);
			
			result.setPaths(sortedPaths);
			result.setNumPaths(result.getPaths().size());
			result.setShortestPathSize(shortestPath.size());
			result.setLongestPathSize(longestPath.size());
			result.setConnected(YES);
		}
		
		return result;
	}

	@Override
	@Cacheable(HazelcastConfig.SHORTEST_PATH_CACHE_NAME)
	public DirectedCityPathInfo findShortestPaths(DirectedCityGraph graph, String origin, String destination) {
		LOGGER.info("Searching for shortest paths from {{}} to {{}}", origin, destination);
		
		DirectedCityPathInfo result = new DirectedCityPathInfo(origin, destination);

		Set<String> visited = new HashSet<>();
		List<String> path = new ArrayList<>();
		
		path.add(origin);
		
		findShortestPaths(graph, origin, destination, visited, path, result);
		
		if (result.getPaths().isEmpty()) {
			LOGGER.info("No path found");
			result.setConnected(NO);
			
		} else {
			LOGGER.info("{} shortest path(s) of size {} found", result.getPaths().size(), result.getPaths().get(0).size());
			result.setNumPaths(result.getPaths().size());
			result.setShortestPathSize(result.getPaths().get(0).size());
			result.setLongestPathSize(-1); // not applicable in case of shortest path search
			result.setConnected(YES);
		}
		
		return result;
	}
	
	private CityConnectionInfo checkIfIndirectlyConnected(
			CityGraph graph, 
			String origin, 
			String destination, 
			CityConnectionInfo result) {
		
		LOGGER.info("Checking indirect connection between {{}} and {{}}", origin, destination);
		
		Set<String> visited = new HashSet<>();
		Queue<String> q = new LinkedList<>();

		visited.add(origin);
		q.add(origin);

		while (!q.isEmpty()) {
			String node = q.remove();

			for (String adj : graph.getAdjMap().get(node)) {
				if (adj.equals(destination)) {
					LOGGER.info("Connection found, returning positive response");
					result.setConnected(YES);
					return result;
				}

				if (!visited.contains(adj)) {
					visited.add(adj);
					q.add(adj);
				}
			}
		}
        
		result.setConnected(NO);
 
		LOGGER.info("Connection not found, returning negative response");
        
		return result;	
	}
	
	private void findAllPaths(
			DirectedCityGraph graph, 
			String node, 
			String destination, 
			Set<String> visited, 
			List<String> path, 
			DirectedCityPathInfo res) {
		
		visited.add(node);
		
		if (node.equals(destination)) {
			res.addPath(new ArrayList<>(path));
			visited.remove(node);
			return;
		}
		
		if (graph.getAdjMap().containsKey(node)) {
			for (String adj : graph.getAdjMap().get(node)) {
				if (!visited.contains(adj)) {
					path.add(adj);
					findAllPaths(graph, adj, destination, visited, path, res);
					path.remove(path.size()-1);
				}
			}
		}
		
		visited.remove(node);
	}
	
	private void findShortestPaths(
			DirectedCityGraph graph, 
			String node, 
			String destination, 
			Set<String> visited, 
			List<String> path, 
			DirectedCityPathInfo result) {
		
		visited.add(node);
		
		if (node.equals(destination)) {
			if (result.getPaths().isEmpty()) {
				result.addPath(new ArrayList<>(path));
			
			} else {
				int shortestSoFar = result.getPaths().get(0).size();
				int currPathSize = path.size();
				
				if (shortestSoFar < currPathSize) {
					// shorter path already found, don't add
				} else if (shortestSoFar == currPathSize) {
					// add same-size path
					result.addPath(new ArrayList<>(path));
				} else {
					// replace existing paths with shorter current path
					result.setPaths(new ArrayList<>());
					result.addPath(new ArrayList<>(path));
				}
			}
			
			visited.remove(node);
			
			return;
		}
		
		if (graph.getAdjMap().containsKey(node)) {
			for (String adj : graph.getAdjMap().get(node)) {
				if (!visited.contains(adj)) {
					path.add(adj);
					findShortestPaths(graph, adj, destination, visited, path, result);
					path.remove(path.size()-1);
				}
			}
		}
		
		visited.remove(node);
	}
	
}
