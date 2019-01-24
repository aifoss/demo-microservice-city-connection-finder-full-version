package com.demo.microservice.city.pathfinder.model;

import static com.demo.microservice.city.pathfinder.util.TestUtil.NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.UNKNOWN_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.YES_CONNECTION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Unit test for {@link CityConnectionInfo}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
public class CityConnectionInfoTest {

	@Test
	public void test() {
		CityConnectionInfo info1 = YES_CONNECTION.clone();
		CityConnectionInfo info2 = YES_CONNECTION.clone();
		
		assertEquals(info1, info2);
		assertEquals(info1.hashCode(), info2.hashCode());
		
		info2 = NO_CONNECTION.clone();
		assertNotEquals(info1, info2);
		
		info2 = UNKNOWN_CONNECTION.clone();
		assertNotEquals(info1, info2);
		
		info2 = info1.clone();
		assertEquals(info1, info2);
		assertEquals(info1.hashCode(), info2.hashCode());
	}

}
