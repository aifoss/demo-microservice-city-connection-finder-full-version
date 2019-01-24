package com.demo.microservice.zuul.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application for netflix-zuul-api-gateway-service.
 * 
 * @author sofia
 * @date 2019-01-20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@ComponentScan("com.demo.microservice.zuul.service")
public class NetflixZuulApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServiceApplication.class, args);
	}

}
