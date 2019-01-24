package com.demo.microservice.city.pathfinder.model;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Data model to encapsulate the result of checking undirected connection between two cities.
 * 
 * @author sofia
 * @date 2019-01-20
 */
public class CityConnectionInfo implements Cloneable {
	
	public enum Connected {
		YES, NO, UNKNOWN
	};
	
	protected String origin;	
	protected String destination;
	
	protected Connected connected;
	
	public CityConnectionInfo() {
	}
	
	public CityConnectionInfo(String origin, String destination) {
		this(origin, destination, Connected.UNKNOWN);
	}
	
	public CityConnectionInfo(String origin, String destination, Connected connected) {
		this.origin = origin;
		this.destination = destination;
		this.connected = connected;
	}
	
	@Override
	public CityConnectionInfo clone() {
		CityConnectionInfo newInfo = new CityConnectionInfo(origin, destination, connected);
		return newInfo;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o.getClass() != this.getClass()) return false;
		
		CityConnectionInfo other = (CityConnectionInfo) o;
		
		return this.origin.equals(other.origin)
				&& this.destination.equals(other.destination)
				&& this.connected == other.connected;
	}
	
	@Override
	public int hashCode() {
		int hash = 31;
		
		hash = (hash * 17) + origin.hashCode();
		hash = (hash * 17) + destination.hashCode();
		hash = (hash * 17) + connected.hashCode();
		
		return hash;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Connected getConnected() {
		return connected;
	}

	public void setConnected(Connected connected) {
		this.connected = connected;
	}
	
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
	
}
