package com.demo.microservice.city.graphbuilder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Configuration for request logging filter.
 * 
 * @author sofia
 * @date 2019-01-21
 */
@Configuration
public class RequestLoggingFilterConfig {

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		 
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(false);
		filter.setIncludeHeaders(false);
		
		return filter;
	}

}
