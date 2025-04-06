package com.gw.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class UserManager {

    private static final String USER_SERVICE_URL = System.getenv().getOrDefault("REST1_SERVICE_URL",
            "http://localhost:8081/rest1/");

    private final RestTemplate restTemplate;

    public UserManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> ouvrirCompetition(String categorie) {
        String url = USER_SERVICE_URL + "ouvrirCompetition";
        Map<String, String> params = new HashMap<>();
        params.put("categorie", categorie);

        return restTemplate.postForEntity(url, params, String.class);
    }

    public ResponseEntity<String> supprimerCompetition(int id) {
        String url = USER_SERVICE_URL + "supprimerCompetition/{id}";
        Map<String, Integer> params = new HashMap<>();
        params.put("id", id);

        return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, params);

    }

    public ResponseEntity<String> modifierCompetition(int id, String etat, String categorie) {
        String url = USER_SERVICE_URL + "modifierCompetition";
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));
        params.put("etat", etat);
        params.put("categorie", categorie);

        return restTemplate.exchange(url, HttpMethod.PUT, null, String.class, params);
    }

    public ResponseEntity<?> getCompetitions(int idCompetition) {
        String url = USER_SERVICE_URL + "getCompetitions/{idCompetition}";
        Map<String, Integer> params = new HashMap<>();
        params.put("idCompetition", idCompetition);

        return restTemplate.getForEntity(url, null, params);
    }

    public ResponseEntity<Map> login(String nomUtilisateur, String mdp) {
        String url = USER_SERVICE_URL + "login";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("nom_utilisateur", nomUtilisateur);
        params.add("mdp", mdp);

        return restTemplate.postForEntity(url, params, Map.class);
    }

    public ResponseEntity<String> signIn(String nomUtilisateur, String mdp) {
        String url = USER_SERVICE_URL + "signIn";
        Map<String, String> params = new HashMap<>();
        params.put("nom_utilisateur", nomUtilisateur);
        params.put("mdp", mdp);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForEntity(url, request, null);
    }

    public ResponseEntity<?> getUtilisateurs(List<Integer> ids) {
        String url = USER_SERVICE_URL + "getUtilisateurs";
        Map<String, Object> params = new HashMap<>();

        if (!(ids == null || ids.isEmpty())) {
            params.put("ids", ids);
        }

        return restTemplate.getForEntity(url, null, params);
    }
}
