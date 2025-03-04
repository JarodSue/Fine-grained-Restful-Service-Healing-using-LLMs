package com.example;



import org.springframework.boot.SpringApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
@EnableEurekaClient
public class ClientApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
