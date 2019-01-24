package com.demo.microservice.city.pathfinder.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Application for city-path-finder-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableCaching
@EnableHystrix
@EnableFeignClients("com.demo.microservice.city.pathfinder.proxy")
@ComponentScan("com.demo.microservice.city.pathfinder")
public class CityPathFinderServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CityPathFinderServiceApplication.class, args);
	}
	
}
