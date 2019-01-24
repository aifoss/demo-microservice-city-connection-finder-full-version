package com.demo.microservice.city.graphbuilder.config;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Unit test for {@link SwaggerConfig}.
 * 
 * @author sofia
 * @date 2019-01-22
 */
public class SwaggerConfigTest {

	@InjectMocks
	private SwaggerConfig swaggerConfig;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_api() {
		Docket docket = swaggerConfig.api();
		assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
		assertEquals(SwaggerConfig.GROUP_NAME, docket.getGroupName());
	}
	
	@Test
	public void test_apiInfo() {
		ApiInfo apiInfo = swaggerConfig.apiInfo();
		assertEquals(SwaggerConfig.TITLE, apiInfo.getTitle());
		assertEquals(SwaggerConfig.DESCRIPTION, apiInfo.getDescription());
		assertEquals(SwaggerConfig.TOS_URL, apiInfo.getTermsOfServiceUrl());
		assertEquals(SwaggerConfig.LICENSE, apiInfo.getLicense());
		assertEquals(SwaggerConfig.LICENSE_URL, apiInfo.getLicenseUrl());
		assertEquals(SwaggerConfig.VERSION, apiInfo.getVersion());
	}

}
