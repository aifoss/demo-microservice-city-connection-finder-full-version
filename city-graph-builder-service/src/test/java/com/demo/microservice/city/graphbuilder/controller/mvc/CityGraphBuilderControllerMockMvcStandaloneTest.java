package com.demo.microservice.city.graphbuilder.controller.mvc;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_ADJ_MAP_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_NODE_SET_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_ADJ_MAP_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_NODE_SET_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_STRING;
import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.microservice.city.common.model.CityGraph;
import com.demo.microservice.city.common.model.DirectedCityGraph;
import com.demo.microservice.city.graphbuilder.controller.CityGraphBuilderController;
import com.demo.microservice.city.graphbuilder.util.TestUtil;

/**
 * MVC test for {@link CityGraphBuilderController} with mock standalone MVC.
 * 
 * @author sofia
 * @date 2019-01-22
 */
@RunWith(MockitoJUnitRunner.class)
public class CityGraphBuilderControllerMockMvcStandaloneTest {

	@InjectMocks
	private CityGraphBuilderController cityGraphBuilderController;
	
	@Mock
	private CityGraph graph;
	
	@Mock
	private DirectedCityGraph directedGraph;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(cityGraphBuilderController).build();
		
		ReflectionTestUtils.setField(cityGraphBuilderController, "graph", GRAPH);
		ReflectionTestUtils.setField(cityGraphBuilderController, "directedGraph", DIRECTED_GRAPH);
	}
	
	@Test
	public void test_getGraphReturnsGraph() throws Exception {
		String apiUri = TestUtil.getGraphApiUri();
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(GRAPH_STRING);
	}
	
	@Test
	public void test_getGraphNodeSetReturnsGraphNodeSet() throws Exception {
		String apiUri = TestUtil.getGraphNodeSetApiUri();
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(GRAPH_NODE_SET_STRING);
	}
	
	@Test
	public void test_getGraphAdjMapReturnsGraphAdjMap() throws Exception {
		String apiUri = TestUtil.getGraphAdjMapApiUri();
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(GRAPH_ADJ_MAP_STRING);
	}
	
	@Test
	public void test_getDirectedGraphReturnsDirectedGraph() throws Exception {
		String apiUri = TestUtil.getDirectedGraphApiUri();
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(DIRECTED_GRAPH_STRING);
	}
	
	@Test
	public void test_getDirectedGraphNodeSetReturnsDirectedGraphNodeSet() throws Exception {
		String apiUri = TestUtil.getDirectedGraphNodeSetApiUri();
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(DIRECTED_GRAPH_NODE_SET_STRING);
	}
	
	@Test
	public void test_getDirectedGraphAdjMapReturnsDirectedGraphAdjMap() throws Exception {
		String apiUri = TestUtil.getDirectedGraphAdjMapApiUri();
		
		MockHttpServletResponse response = mvc.perform(get(apiUri)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(DIRECTED_GRAPH_ADJ_MAP_STRING);
	}
	
}
