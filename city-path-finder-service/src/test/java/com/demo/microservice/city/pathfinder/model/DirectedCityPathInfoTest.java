package com.demo.microservice.city.pathfinder.model;

import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Unit test for {@link DirectedCityPathInfo}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
public class DirectedCityPathInfoTest {

	@Test
	public void test() {
		DirectedCityPathInfo info1 = ALL_PATH_INFO.clone();
		DirectedCityPathInfo info2 = ALL_PATH_INFO.clone();
		
		assertEquals(info1, info2);
		assertEquals(info1.hashCode(), info2.hashCode());
		
		info2 = ALL_PATH_INFO_WITH_NO_CONNECTION.clone();
		assertNotEquals(info1, info2);
		
		info1 = SHORTEST_PATH_INFO.clone();
		info2 = SHORTEST_PATH_INFO.clone();
		
		assertEquals(info1, info2);
		assertEquals(info1.hashCode(), info2.hashCode());
		
		info2 = SHORTEST_PATH_INFO_WITH_NO_CONNECTION.clone();
		assertNotEquals(info1, info2);
		
		info2 = info1.clone();
		assertEquals(info1, info2);
		assertEquals(info1.hashCode(), info2.hashCode());
	}

}
