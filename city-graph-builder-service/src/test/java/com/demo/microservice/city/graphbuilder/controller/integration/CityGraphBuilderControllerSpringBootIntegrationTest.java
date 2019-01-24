package com.demo.microservice.city.graphbuilder.controller.integration;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_ADJ_MAP;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_NODE_SET;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_ADJ_MAP;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_NODE_SET;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.SortedMap;
import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.graphbuilder.app.CityGraphBuilderServiceApplication;
import com.demo.microservice.city.graphbuilder.controller.CityGraphBuilderController;
import com.demo.microservice.city.graphbuilder.util.TestConfig;
import com.demo.microservice.city.graphbuilder.util.TestUtil;

/**
 * Integration test for {@link CityGraphBuilderController}.
 * 
 * @author sofia
 * @date 2019-01-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT, 
		classes = CityGraphBuilderServiceApplication.class)
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:test.properties")
@SuppressWarnings("rawtypes")
public class CityGraphBuilderControllerSpringBootIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test_getGraphReturnsGraph() throws Exception {
		String apiUri = TestUtil.getGraphApiUri();
		
		ResponseEntity<CityGraph> response = restTemplate.getForEntity(apiUri, CityGraph.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().equals(GRAPH));	
	}

	@Test
	public void test_getGraphNodeSetReturnsGraphNodeSet() throws Exception {
		String apiUri = TestUtil.getGraphNodeSetApiUri();
		
		ResponseEntity<SortedSet> response = restTemplate.getForEntity(apiUri, SortedSet.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().equals(GRAPH_NODE_SET));
	}
	
	@Test
	public void test_getGraphAdjMapReturnsGraphAdjMap() throws Exception {
		String apiUri = TestUtil.getGraphAdjMapApiUri();
		
		ResponseEntity<SortedMap> response = restTemplate.getForEntity(apiUri, SortedMap.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().equals(GRAPH_ADJ_MAP));	
	}
	
	@Test
	public void test_getDirectedGraphReturnsDirectedGraph() throws Exception {
		String apiUri = TestUtil.getDirectedGraphApiUri();
		
		ResponseEntity<DirectedCityGraph> response = restTemplate.getForEntity(apiUri, DirectedCityGraph.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().equals(DIRECTED_GRAPH));	
	}

	@Test
	public void test_getDirectedGraphNodeSetReturnsDirectedGraphNodeSet() throws Exception {
		String apiUri = TestUtil.getDirectedGraphNodeSetApiUri();
		
		ResponseEntity<SortedSet> response = restTemplate.getForEntity(apiUri, SortedSet.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().equals(DIRECTED_GRAPH_NODE_SET));
	}
	
	@Test
	public void test_getDirectedGraphAdjMapReturnsDirectedGraphAdjMap() throws Exception {
		String apiUri = TestUtil.getDirectedGraphAdjMapApiUri();
		
		ResponseEntity<SortedMap> response = restTemplate.getForEntity(apiUri, SortedMap.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().equals(DIRECTED_GRAPH_ADJ_MAP));	
	}

}
