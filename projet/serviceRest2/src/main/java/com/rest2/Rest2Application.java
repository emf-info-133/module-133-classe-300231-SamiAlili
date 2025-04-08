package com.rest2;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.rest2.repository.ParticipationRepository;
import com.rest2.repository.VoteRepository;
import com.rest2.service.ParticipationService;
import com.rest2.service.VoteService;

@SpringBootApplication
@Configuration
public class Rest2Application {

	public static void main(String[] args) {
		SpringApplication.run(Rest2Application.class, args);
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
	public VoteService voteService(VoteRepository voteRepository, RestTemplate restTemplate) {
		return new VoteService(voteRepository, restTemplate);
	}

	@Bean
	public ParticipationService participationService(ParticipationRepository participationRepository,
			RestTemplate restTemplate) {
		return new ParticipationService(participationRepository, restTemplate);
	}
}
