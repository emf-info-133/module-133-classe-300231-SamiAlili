package com.gw.manager;

import org.springframework.web.client.RestTemplate;

public class UserManager {

    private static final String USER_SERVICE_URL = System.getenv().getOrDefault("REST1_SERVICE_URL",
            "http://localhost:8081/rest1/");

    private final RestTemplate restTemplate;

    public UserManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
