package com.demo.microservice.city.pathfinder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Data model to encapsulate the result of searching for directed paths from origin to destination.
 * 
 * @author sofia
 * @date 2019-01-20
 */
public class DirectedCityPathInfo extends CityConnectionInfo {

	protected int numPaths;
	protected int shortestPathSize;
	protected int longestPathSize;	
	
	protected List<List<String>> paths;

	public DirectedCityPathInfo() {
		super();
		paths = new ArrayList<>();
	}
	
	public DirectedCityPathInfo(String origin, String destination) {
		super(origin, destination);
		paths = new ArrayList<>();
	}
	
	public DirectedCityPathInfo(String origin, String destination, Connected connected) {
		super(origin, destination, connected);
		paths = new ArrayList<>();
	}
	
	public void addPath(List<String> path) {
		paths.add(path);
	}
	
	@Override
	public DirectedCityPathInfo clone() {
		DirectedCityPathInfo newInfo = new DirectedCityPathInfo(origin, destination, connected);
		
		List<List<String>> newPaths = new ArrayList<>();
		for (List<String> path : paths) {
			newPaths.add(new ArrayList<>(path));
		}

		newInfo.setNumPaths(numPaths);
		newInfo.setShortestPathSize(shortestPathSize);
		newInfo.setLongestPathSize(longestPathSize);
		newInfo.setPaths(newPaths);
		
		return newInfo;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		
		DirectedCityPathInfo other = (DirectedCityPathInfo) o;
		
		return this.numPaths == other.numPaths
				&& this.shortestPathSize == other.shortestPathSize
				&& this.longestPathSize == other.longestPathSize
				&& this.paths.equals(other.paths);
	}
	
	@Override
	public int hashCode() {
		int hash = super.hashCode();
		
		hash = (hash * 17) + Integer.hashCode(numPaths);
		hash = (hash * 17) + Integer.hashCode(shortestPathSize);
		hash = (hash * 17) + Integer.hashCode(longestPathSize);
		hash = (hash * 17) + paths.hashCode();
		
		return hash;
	}
	
	public int getNumPaths() {
		return numPaths;
	}

	public void setNumPaths(int numPaths) {
		this.numPaths = numPaths;
	}
	
	public int getShortestPathSize() {
		return shortestPathSize;
	}

	public void setShortestPathSize(int shortestPathSize) {
		this.shortestPathSize = shortestPathSize;
	}
	
	public int getLongestPathSize() {
		return longestPathSize;
	}

	public void setLongestPathSize(int longestPathSize) {
		this.longestPathSize = longestPathSize;
	}

	public List<List<String>> getPaths() {
		return paths;
	}

	public void setPaths(List<List<String>> paths) {
		this.paths = paths;
	}

}
