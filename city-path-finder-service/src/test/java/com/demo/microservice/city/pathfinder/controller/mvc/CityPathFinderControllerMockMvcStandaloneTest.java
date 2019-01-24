package com.demo.microservice.city.pathfinder.controller.mvc;

import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_STRING;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ALL_PATH_INFO_WITH_NO_CONNECTION_STRING;
import static com.demo.microservice.city.pathfinder.util.TestUtil.DESTINATION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.NO_CONNECTION_STRING;
import static com.demo.microservice.city.pathfinder.util.TestUtil.ORIGIN;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_STRING;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_WITH_NO_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.SHORTEST_PATH_INFO_WITH_NO_CONNECTION_STRING;
import static com.demo.microservice.city.pathfinder.util.TestUtil.UNKNOWN_CITY;
import static com.demo.microservice.city.pathfinder.util.TestUtil.YES_CONNECTION;
import static com.demo.microservice.city.pathfinder.util.TestUtil.YES_CONNECTION_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.pathfinder.controller.CityPathFinderController;
import com.demo.microservice.city.pathfinder.proxy.CityGraphBuilderServiceProxy;
import com.demo.microservice.city.pathfinder.service.CityPathFinderService;
import com.demo.microservice.city.pathfinder.util.TestUtil;

/**
 * MVC test for {@link CityPathFinderController} using standalone mock MVC.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RunWith(MockitoJUnitRunner.class)
public class CityPathFinderControllerMockMvcStandaloneTest {

	@InjectMocks
	private CityPathFinderController cityPathFinderController;
	
	@Mock
	private CityGraphBuilderServiceProxy cityGraphBuilderService;
	
	@Mock
	private CityPathFinderService cityPathFinderService;
	
	@Mock
	private CityGraph graph;
	
	@Mock
	private DirectedCityGraph directedGraph;

	private MockMvc mvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(cityPathFinderController).build();
	}
	
	@Test
	public void test_givenServiceWillReturnYesConnection_returnsYesConnection() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		given(cityGraphBuilderService.getGraph()).willReturn(graph);
		given(cityPathFinderService.checkIfConnected(
				graph, origin.toLowerCase(), destination.toLowerCase())).willReturn(YES_CONNECTION);
		
		String apiUri = TestUtil.getConnectedApiUri(origin, destination);
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(YES_CONNECTION_STRING);
	}
	
	@Test
	public void test_givenServiceWillReturnNoConnection_returnsNoConnection() throws Exception {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		given(cityGraphBuilderService.getGraph()).willReturn(graph);
		given(cityPathFinderService.checkIfConnected(
				graph, origin.toLowerCase(), destination.toLowerCase())).willReturn(NO_CONNECTION);
		
		String apiUri = TestUtil.getConnectedApiUri(origin, destination);
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(NO_CONNECTION_STRING);
	}
	
	@Test
	public void test_givenServiceWillReturnAllPathInfoWithYesConnection_returnsAllPathInfoWithYesConnection() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findAllPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(ALL_PATH_INFO);
		
		String apiUri = TestUtil.getFindAllPathsApiUri(origin, destination);
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(ALL_PATH_INFO_STRING);
	}
	
	@Test
	public void test_givenServiceWillReturnAllPathInfoWithNoConnection_returnsAllPathInfoWithNoConnection() throws Exception {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findAllPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(ALL_PATH_INFO_WITH_NO_CONNECTION);
		
		System.out.println(ALL_PATH_INFO_WITH_NO_CONNECTION.toString());
		
		String apiUri = TestUtil.getFindAllPathsApiUri(origin, destination);
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(ALL_PATH_INFO_WITH_NO_CONNECTION_STRING);
		
		System.out.println(ALL_PATH_INFO_WITH_NO_CONNECTION_STRING);
	}
	
	@Test
	public void test_givenServiceWillReturnShortestPathInfoWithYesConnection_returnsShortestPathInfoWithYesConnection() throws Exception {
		String origin = ORIGIN;
		String destination = DESTINATION;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findShortestPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(SHORTEST_PATH_INFO);
		
		String apiUri = TestUtil.getFindShortestPathsApiUri(origin, destination);
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(SHORTEST_PATH_INFO_STRING);
	}
	
	@Test
	public void test_givenServiceWillReturnShortestPathInfoWithNoConnection_returnsShortestPathInfoWithNoConnection() throws Exception {
		String origin = ORIGIN;
		String destination = UNKNOWN_CITY;
		
		given(cityGraphBuilderService.getDirectedGraph()).willReturn(directedGraph);
		given(cityPathFinderService.findShortestPaths(
				directedGraph, origin.toLowerCase(), destination.toLowerCase())).willReturn(SHORTEST_PATH_INFO_WITH_NO_CONNECTION);
		
		String apiUri = TestUtil.getFindShortestPathsApiUri(origin, destination);
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(SHORTEST_PATH_INFO_WITH_NO_CONNECTION_STRING);
		
		System.out.println(response.getContentAsString());
		System.out.println(SHORTEST_PATH_INFO_WITH_NO_CONNECTION_STRING);
	}

}
