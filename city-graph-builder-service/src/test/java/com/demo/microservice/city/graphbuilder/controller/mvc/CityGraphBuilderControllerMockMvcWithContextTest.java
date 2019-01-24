package com.demo.microservice.city.graphbuilder.controller.mvc;

import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_ADJ_MAP_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_NODE_SET_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.DIRECTED_GRAPH_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_ADJ_MAP_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_NODE_SET_STRING;
import static com.demo.microservice.city.graphbuilder.util.TestUtil.GRAPH_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.microservice.city.graphbuilder.controller.CityGraphBuilderController;
import com.demo.microservice.city.graphbuilder.util.TestConfig;
import com.demo.microservice.city.graphbuilder.util.TestUtil;

/**
 * MVC test for {@link CityGraphBuilderController} with mock MVC with context.
 * 
 * @author sofia
 * @date 2019-01-23
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CityGraphBuilderController.class)
@ContextConfiguration(classes = {CityGraphBuilderController.class, TestConfig.class})
public class CityGraphBuilderControllerMockMvcWithContextTest {

	@Autowired
	private MockMvc mvc;
	
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
