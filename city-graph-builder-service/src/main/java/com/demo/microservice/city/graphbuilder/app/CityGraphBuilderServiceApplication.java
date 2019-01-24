package com.demo.microservice.city.graphbuilder.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Application for city-graph-builder-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@ComponentScan("com.demo.microservice.city.graphbuilder")
public class CityGraphBuilderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityGraphBuilderServiceApplication.class, args);
	}

}
