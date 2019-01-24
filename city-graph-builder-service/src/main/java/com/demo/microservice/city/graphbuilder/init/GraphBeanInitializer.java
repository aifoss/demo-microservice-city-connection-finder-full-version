package com.demo.microservice.city.graphbuilder.init;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.graphbuilder.service.CityGraphBuilderService;

/**
 * Initializer for {@link CityGraph} and {@link DirectedCityGraph} beans.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@Component
public class GraphBeanInitializer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GraphBeanInitializer.class);

	@Autowired
	private CityGraphBuilderService cityGraphBuilderService;

	@Bean(name = "graph")
	public CityGraph graph() {
		LOGGER.info("Initializing graph bean ...");
		
		try {
			return cityGraphBuilderService.buildGraph();
		} catch (IOException e) {
			throw new RuntimeException(String.format("Cannot build undirected graph of cities... Abort!: %s", e.getMessage()));
		}
	}

	@Bean(name = "directedGraph")
	public DirectedCityGraph directedGraph() {
		LOGGER.info("Initializing directed graph bean ...");
		
		try {
			return cityGraphBuilderService.buildDirectedGraph();
		} catch (IOException e) {
			throw new RuntimeException(String.format("Cannot build directed graph of cities... Abort!: %s", e.getMessage()));
		}
	}
	
}
