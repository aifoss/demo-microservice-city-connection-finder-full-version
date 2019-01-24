package com.demo.microservice.city.pathfinder.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

/**
 * Configuration for Hazelcast cache.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@Configuration
@Profile("hazelcast-cache")
public class HazelcastConfig {
	
	public static final String HAZELCAST_INSTANCE_NAME = "hazelcast-cache";
	public static final String GRAPH_CACHE_NAME = "graph";
	public static final String DIRECTED_GRAPH_CACHE_NAME = "directed-graph";
	public static final String CITY_CONNECTION_CACHE_NAME = "city-connections";
	public static final String ALL_PATH_CACHE_NAME = "all-paths";
	public static final String SHORTEST_PATH_CACHE_NAME = "shortest-paths";
	
	protected static final int TIME_TO_LIVE_IN_SECONDS = 20;
	protected static final EvictionPolicy EVICTION_POLICY = EvictionPolicy.LFU;
	
	@Bean
	public Config hazelcastConfig() {
        Config config = new Config();
        
        config.setInstanceName(HAZELCAST_INSTANCE_NAME);

        MapConfig graphCache = new MapConfig();
        config.getMapConfigs().put(GRAPH_CACHE_NAME, graphCache);
        
        MapConfig directedGraphCache = new MapConfig();
        config.getMapConfigs().put(DIRECTED_GRAPH_CACHE_NAME, directedGraphCache);
        
        MapConfig cityConnectionCache = new MapConfig();
        cityConnectionCache.setTimeToLiveSeconds(TIME_TO_LIVE_IN_SECONDS);
        cityConnectionCache.setEvictionPolicy(EVICTION_POLICY);
        config.getMapConfigs().put(CITY_CONNECTION_CACHE_NAME, cityConnectionCache);

        MapConfig allPathCache = new MapConfig();
        allPathCache.setTimeToLiveSeconds(TIME_TO_LIVE_IN_SECONDS);
        allPathCache.setEvictionPolicy(EVICTION_POLICY);
        config.getMapConfigs().put(ALL_PATH_CACHE_NAME, allPathCache);

        MapConfig shortestPathCache = new MapConfig();
        shortestPathCache.setTimeToLiveSeconds(TIME_TO_LIVE_IN_SECONDS);
        shortestPathCache.setEvictionPolicy(EVICTION_POLICY);
        config.getMapConfigs().put(SHORTEST_PATH_CACHE_NAME, shortestPathCache);
        
        return config;
	}

	@Bean
	public HazelcastInstance hazelcastInstance() {
		return Hazelcast.newHazelcastInstance(hazelcastConfig());
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new HazelcastCacheManager(hazelcastInstance());
	}

}
