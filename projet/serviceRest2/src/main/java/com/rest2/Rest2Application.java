package com.rest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.rest2.repository.VoteRepository;
import com.rest2.service.VoteService;

@SpringBootApplication
@Configuration
public class Rest2Application {

	public static void main(String[] args) {
		SpringApplication.run(Rest2Application.class, args);
	}	

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public VoteService voteService(VoteRepository voteRepository) {
		return new VoteService(voteRepository);
	}
}
