package com.demo.microservice.zuul.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Nominal test for {@link NetflixZuulApiGatewayServiceApplication}.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class NetflixZuulApiGatewayServiceApplicationTest {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void main() {
	}

}
