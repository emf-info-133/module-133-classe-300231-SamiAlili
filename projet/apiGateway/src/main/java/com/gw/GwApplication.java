package com.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.gw.manager.UserManager;

@SpringBootApplication
@Configuration
public class GwApplication {

	public static void main(String[] args) {
		SpringApplication.run(GwApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public UserManager userManager(RestTemplate restTemplate) {
		return new UserManager(restTemplate);
	}

}
