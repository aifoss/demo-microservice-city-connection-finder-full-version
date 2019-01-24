package com.demo.microservice.city.pathfinder.controller.integration;

import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static com.demo.microservice.city.pathfinder.util.TestUtil.UNKNOWN_CONNECTION;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.microservice.city.pathfinder.app.CityPathFinderServiceApplication;
import com.demo.microservice.city.pathfinder.controller.HystrixFallbackDemoController;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.util.TestUtil;

/**
 * Integration test for {@link HystrixFallbackDemoController}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT, 
		classes = CityPathFinderServiceApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class HystrixFallbackDemoControllerSpringBootIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test_returnsUnknownConnectionForConnected() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		String apiUri = TestUtil.getHystrixConnectedApiUri(origin, destination);
		
		ResponseEntity<CityConnectionInfo> response = restTemplate.getForEntity(apiUri, CityConnectionInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(UNKNOWN_CONNECTION);	
	}
	
	@Test
	public void test_returnsUnknownConnectionForFindAllPaths() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		String apiUri = TestUtil.getHystrixFindAllPathsApiUri(origin, destination);
		
		ResponseEntity<CityConnectionInfo> response = restTemplate.getForEntity(apiUri, CityConnectionInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(UNKNOWN_CONNECTION);	
	}
	
	@Test
	public void test_returnsUnknownConnectionForFindShortestPaths() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		String apiUri = TestUtil.getHystrixFindShortestPathsApiUri(origin, destination);
		
		ResponseEntity<CityConnectionInfo> response = restTemplate.getForEntity(apiUri, CityConnectionInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(UNKNOWN_CONNECTION);	
	}
	
}
