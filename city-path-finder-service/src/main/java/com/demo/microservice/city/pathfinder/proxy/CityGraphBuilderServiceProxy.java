package com.demo.microservice.city.pathfinder.proxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.config.HazelcastConfig;

/**
 * Proxy for city-graph-builder-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@FeignClient(name = "netflix-zuul-api-gateway-service")
@RibbonClient(name = "city-graph-builder-service")
public interface CityGraphBuilderServiceProxy {

	@GetMapping("/city-graph-builder-service/graph-builder/graph")
	@Cacheable(HazelcastConfig.GRAPH_CACHE_NAME)
	CityGraph getGraph();

	@GetMapping("/city-graph-builder-service/graph-builder/directed-graph")
	@Cacheable(HazelcastConfig.DIRECTED_GRAPH_CACHE_NAME)
	DirectedCityGraph getDirectedGraph();

}
