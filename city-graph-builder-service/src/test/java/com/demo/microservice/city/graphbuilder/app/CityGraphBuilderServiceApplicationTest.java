package com.demo.microservice.city.graphbuilder.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Nominal test for {@link CityGraphBuilderServiceApplication}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CityGraphBuilderServiceApplicationTest {

	@Test
	public void contextLoads() {
	}

	@Test
	public void main() {		
	}

}
