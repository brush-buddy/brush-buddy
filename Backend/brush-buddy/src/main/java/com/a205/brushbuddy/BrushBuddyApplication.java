package com.a205.brushbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BrushBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrushBuddyApplication.class, args);
	}

}
