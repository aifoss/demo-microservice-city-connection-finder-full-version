package com.demo.microservice.city.pathfinder.controller.mvc;

import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.demo.microservice.city.pathfinder.controller.HystrixFallbackDemoController;
import com.demo.microservice.city.pathfinder.util.TestUtil;

/**
 * MVC test for {@link HystrixFallbackDemoController} with standalone mock MVC.
 * 
 * @author sofia
 * @date 2019-01-21
 */
public class HystrixFallbackDemoControllerMockMvcStandaloneTest {

	@InjectMocks
	private HystrixFallbackDemoController hystrixFallbackDemoController;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(HystrixFallbackDemoController.class).build();
	}
	
	@Test(expected = NestedServletException.class)
	public void test_hystrixCheckIfConnected() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		String apiUri = TestUtil.getHystrixConnectedApiUri(origin, destination);
		
		mvc.perform(get(apiUri)).andReturn().getResponse();
	}
	
	@Test(expected = NestedServletException.class)
	public void test_hystrixFindAllPaths() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		String apiUri = TestUtil.getHystrixFindAllPathsApiUri(origin, destination);
		
		mvc.perform(get(apiUri)).andReturn().getResponse();
	}
	
	@Test(expected = NestedServletException.class)
	public void test_hystrixFindShortestPaths() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		String apiUri = TestUtil.getHystrixFindShortestPathsApiUri(origin, destination);
		
		mvc.perform(get(apiUri)).andReturn().getResponse();
	}
	
}
