package com.demo.microservice.city.graphbuilder.util;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;

@TestConfiguration
public class TestConfig {

	@Bean
	public CityGraph graph() {
		return GRAPH;
	}
	
	@Bean
	public DirectedCityGraph directedGraph() {
		return DIRECTED_GRAPH;
	}

}
