package com.demo.microservice.city.pathfinder.controller.unit;

import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.demo.microservice.city.pathfinder.controller.HystrixFallbackDemoController;

/**
 * Unit test for {@link HystrixFallbackDemoController}.
 * 
 * @author sofia
 * @demo 2019-01-21
 */
public class HystrixFallbackDemoControllerTest {

	@InjectMocks
	private HystrixFallbackDemoController hystrixFallbackDemoController;
	
	@Test(expected = RuntimeException.class)
	public void test_hystrixCheckIfConnected() {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		hystrixFallbackDemoController.hystrixCheckIfConnected(origin, destination);
	}
	
	@Test(expected = RuntimeException.class)
	public void test_hystrixFindAllPaths() {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		hystrixFallbackDemoController.hystrixCheckIfConnected(origin, destination);
	}
	
	@Test(expected = RuntimeException.class)
	public void test_hystrixFindShortestPaths() {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		hystrixFallbackDemoController.hystrixCheckIfConnected(origin, destination);
	}

}
