package com.demo.microservice.city.pathfinder.controller.mvc;

import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.demo.microservice.city.pathfinder.controller.HystrixFallbackDemoController;
import com.demo.microservice.city.pathfinder.util.TestUtil;

/**
 * MVC test for {@link HystrixFallbackDemoController} with mock MVC with context.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HystrixFallbackDemoController.class)
@ContextConfiguration(classes = HystrixFallbackDemoController.class)
public class HystrixFallbackDemoControllerMockMvcWithContext {

	@Autowired
	private MockMvc mvc;
	
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
