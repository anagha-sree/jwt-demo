package com.stackroute.newsapp;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.newsapp.config.JWTFilter;

@SpringBootApplication
public class NewsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsappApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<?> filterRegistrationBean(){
		FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
		filter.addUrlPatterns("/api/v1/user");
		filter.setFilter(new JWTFilter());
		return filter;
		
		
	}

}
