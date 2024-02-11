package com.a205.brushbuddy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:5173", "https://brush-buddy.duckdns.org", "http://localhost:8000") // TODO : 프론트 도메인으로 변경
			.allowedMethods("GET", "POST", "PUT", "DELETE","OPTION")
			.allowedHeaders("*")
			.allowCredentials(true)
			.maxAge(3600);
	}
}
