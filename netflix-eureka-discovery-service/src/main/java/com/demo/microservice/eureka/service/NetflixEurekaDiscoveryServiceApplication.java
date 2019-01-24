package com.demo.microservice.eureka.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Application for netflix-eureka-discovery-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaDiscoveryServiceApplication.class, args);
	}

}
