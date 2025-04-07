package com.gw;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
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
		RestTemplate restTemplate = new RestTemplate();

		// Désactive les exceptions pour les codes 4xx/5xx
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
			}
		});

		return restTemplate;
	}

	@Bean
	public UserManager userManager(RestTemplate restTemplate) {
		return new UserManager(restTemplate);
	}

}
