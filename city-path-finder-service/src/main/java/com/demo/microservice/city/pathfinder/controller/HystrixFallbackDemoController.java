package com.demo.microservice.city.pathfinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo.Connected;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;

/**
 * Rest controller to demonstrate falling back using Hystrix.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RestController
@RequestMapping("/hystrix")
@EnableHystrix
public class HystrixFallbackDemoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HystrixFallbackDemoController.class);
	
	private static final String RESPONSE_FORMAT = "application/json";
	
	@ApiOperation(
			value = "Demonstrate Hystrix fallback for method to check city connections", 
			response = CityConnectionInfo.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/connected/origin/{origin}/destination/{destination}")
	@HystrixCommand(fallbackMethod = "createFallbackCityConnectionInfo")
	public CityConnectionInfo hystrixCheckIfConnected(
			@PathVariable String origin,
			@PathVariable String destination) {
		
		throw new RuntimeException("Some error occurred");
	}

	@ApiOperation(
			value = "Demonstrate Hystric fallback for method to get all paths from origin to destination", 
			response = DirectedCityPathInfo.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/paths-all/origin/{origin}/destination/{destination}") 
	@HystrixCommand(fallbackMethod = "createFallbackDirectedCityPathInfo")
	public DirectedCityPathInfo hystrixFindAllPaths(
			@PathVariable String origin,
			@PathVariable String destination) {
		
		throw new RuntimeException("Some error occurred");
	}
	
	@ApiOperation(
			value = "Demonstrate Hystric fallback for method to get shortest paths from origin to destination", 
			response = DirectedCityPathInfo.class, 
			produces = RESPONSE_FORMAT)
	@GetMapping("/paths-shortest/origin/{origin}/destination/{destination}")
	@HystrixCommand(fallbackMethod = "createFallbackDirectedCityPathInfo")
	public DirectedCityPathInfo histryxFindShortestPaths(
			@PathVariable String origin,
			@PathVariable String destination) {
		
		throw new RuntimeException("Some error occurred");
	}
		
	public CityConnectionInfo createFallbackCityConnectionInfo(String origin, String destination) {
		LOGGER.info("Falling back ... will return default response");
		return new CityConnectionInfo(origin.toLowerCase(), destination.toLowerCase(), Connected.UNKNOWN);
	}
	
	public DirectedCityPathInfo createFallbackDirectedCityPathInfo(String origin, String destination) {
		LOGGER.info("Falling back ... will return default response");
		return new DirectedCityPathInfo(origin.toLowerCase(), destination.toLowerCase(), Connected.UNKNOWN);
	}

}
