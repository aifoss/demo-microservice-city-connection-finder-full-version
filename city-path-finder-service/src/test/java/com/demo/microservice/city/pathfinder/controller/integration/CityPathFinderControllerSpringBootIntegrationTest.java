package com.demo.microservice.city.pathfinder.controller.integration;

import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.UNKNOWN_CITY;
import static com.demo.microservice.city.pathfinder.util.TestUtil.YES_CONNECTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.app.CityPathFinderServiceApplication;
import com.demo.microservice.city.pathfinder.controller.CityPathFinderController;
import com.demo.microservice.city.pathfinder.model.CityConnectionInfo;
import com.demo.microservice.city.pathfinder.model.DirectedCityPathInfo;
import com.demo.microservice.city.pathfinder.proxy.CityGraphBuilderServiceProxy;
import com.demo.microservice.city.pathfinder.service.CityPathFinderService;
import com.demo.microservice.city.pathfinder.util.TestUtil;

/**
 * Integration test for {@link CityPathFinderController}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT, 
		classes = CityPathFinderServiceApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class CityPathFinderControllerSpringBootIntegrationTest {

	@MockBean
	private CityGraphBuilderServiceProxy cityGraphBuilderService;
	
	@MockBean
	private CityPathFinderService cityPathFinderService;
	
	@MockBean
	private CityGraph graph;
	
	@MockBean
	private DirectedCityGraph directedGraph;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test_returnsYesConnectionIfThereIsConnectionBetweenCities() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		given(cityGraphBuilderService.getGraph()).willReturn(graph);
		given(cityPathFinderService.checkIfConnected(
				graph, origin.toLowerCase(), destination.toLowerCase())).willReturn(YES_CONNECTION);
		
		String apiUri = TestUtil.getConnectedApiUri(origin, destination);
		
		ResponseEntity<CityConnectionInfo> response = restTemplate.getForEntity(apiUri, CityConnectionInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(YES_CONNECTION);	
	}
	
	@Test
	public void test_returnsNoConnectionIfThereIsNoConnectionBetweenCities() throws Exception {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		given(cityGraphBuilderService.getGraph()).willReturn(graph);
		given(cityPathFinderService.checkIfConnected(
				graph, origin.toLowerCase(), destination.toLowerCase())).willReturn(NO_CONNECTION);
		
		String apiUri = TestUtil.getConnectedApiUri(origin, destination);
		
		ResponseEntity<CityConnectionInfo> response = restTemplate.getForEntity(apiUri, CityConnectionInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(NO_CONNECTION);	
	}
	
	@Test
	public void test_returnsAllPathInfoWithYesConnectionWhenThereIsConnection() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findAllPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(ALL_PATH_INFO);
		
		String apiUri = TestUtil.getFindAllPathsApiUri(origin, destination);
		
		ResponseEntity<DirectedCityPathInfo> response = restTemplate.getForEntity(apiUri, DirectedCityPathInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(ALL_PATH_INFO);
	}
	
	@Test
	public void test_returnsAllPathInfoWithNoConnectionWhenThereIsNoConnection() throws Exception {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findAllPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(ALL_PATH_INFO_WITH_NO_CONNECTION);
		
		String apiUri = TestUtil.getFindAllPathsApiUri(origin, destination);
		
		ResponseEntity<DirectedCityPathInfo> response = restTemplate.getForEntity(apiUri, DirectedCityPathInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(ALL_PATH_INFO_WITH_NO_CONNECTION);
	}
	
	@Test
	public void test_returnsShortestPathInfoWithYesConnectionWhenThereIsConnection() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findShortestPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(SHORTEST_PATH_INFO);
	
		String apiUri = TestUtil.getFindShortestPathsApiUri(origin, destination);
		
		ResponseEntity<DirectedCityPathInfo> response = restTemplate.getForEntity(apiUri, DirectedCityPathInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(SHORTEST_PATH_INFO);	
	}
	
	@Test
	public void test_returnsShortestPathInfoWithNoConnectionWithNoConnectionWhenThereIsNoConnection() throws Exception {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findShortestPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(SHORTEST_PATH_INFO_WITH_NO_CONNECTION);
		
		String apiUri = TestUtil.getFindShortestPathsApiUri(origin, destination);
		
		ResponseEntity<DirectedCityPathInfo> response = restTemplate.getForEntity(apiUri, DirectedCityPathInfo.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(SHORTEST_PATH_INFO_WITH_NO_CONNECTION);
	}

}
