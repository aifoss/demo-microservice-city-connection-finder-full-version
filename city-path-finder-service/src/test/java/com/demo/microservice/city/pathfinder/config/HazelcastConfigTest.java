package com.demo.microservice.city.pathfinder.config;

import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.ALL_PATH_CACHE_NAME;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.CITY_CONNECTION_CACHE_NAME;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.DIRECTED_GRAPH_CACHE_NAME;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.EVICTION_POLICY;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.GRAPH_CACHE_NAME;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.HAZELCAST_INSTANCE_NAME;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.SHORTEST_PATH_CACHE_NAME;
import static com.demo.microservice.city.pathfinder.config.HazelcastConfig.TIME_TO_LIVE_IN_SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

/**
 * Unit test for {@link HazelcastConfig}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
public class HazelcastConfigTest {
	
	@InjectMocks
	private HazelcastConfig hazelcastConfig;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_hazelcastConfig() {
		Config config = hazelcastConfig.hazelcastConfig();
		
		assertEquals(HAZELCAST_INSTANCE_NAME, config.getInstanceName());
		
		assertTrue(config.getMapConfigs().containsKey(GRAPH_CACHE_NAME));
		assertTrue(config.getMapConfigs().containsKey(DIRECTED_GRAPH_CACHE_NAME));
		
		assertTrue(config.getMapConfigs().containsKey(CITY_CONNECTION_CACHE_NAME));
		MapConfig cityConnectionCache = config.getMapConfig(CITY_CONNECTION_CACHE_NAME);
		assertEquals(TIME_TO_LIVE_IN_SECONDS, cityConnectionCache.getTimeToLiveSeconds());
		assertEquals(EVICTION_POLICY, cityConnectionCache.getEvictionPolicy());
		
		assertTrue(config.getMapConfigs().containsKey(ALL_PATH_CACHE_NAME));
		MapConfig allPathCache = config.getMapConfig(ALL_PATH_CACHE_NAME);
		assertEquals(TIME_TO_LIVE_IN_SECONDS, allPathCache.getTimeToLiveSeconds());
		assertEquals(EVICTION_POLICY, allPathCache.getEvictionPolicy());
		
		assertTrue(config.getMapConfigs().containsKey(SHORTEST_PATH_CACHE_NAME));
		MapConfig shortestPathCache = config.getMapConfig(SHORTEST_PATH_CACHE_NAME);
		assertEquals(TIME_TO_LIVE_IN_SECONDS, shortestPathCache.getTimeToLiveSeconds());
		assertEquals(EVICTION_POLICY, shortestPathCache.getEvictionPolicy());
	}
	
}
