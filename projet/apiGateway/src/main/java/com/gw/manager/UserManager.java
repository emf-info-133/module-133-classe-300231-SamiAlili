package com.gw.manager;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class UserManager {

    private static final String USER_SERVICE_URL = System.getenv().getOrDefault("REST1_SERVICE_URL",
            "http://localhost:8081/rest1/");

    private final RestTemplate restTemplate;

    public UserManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Map> ouvrirCompetition(String categorie) {
        String url = USER_SERVICE_URL + "ouvrirCompetition";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("categorie", categorie);

        return restTemplate.postForEntity(url, params, Map.class);
    }

    public ResponseEntity<Map> supprimerCompetition(int id) {
        String url = USER_SERVICE_URL + "supprimerCompetition/{id}";
        MultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
        params.add("id", id);

        return restTemplate.exchange(url, HttpMethod.DELETE, null, Map.class, params);

    }

    public ResponseEntity<Map> modifierCompetition(int id, String etat, String categorie) {
        String url = USER_SERVICE_URL + "modifierCompetition";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", String.valueOf(id));
        params.add("etat", etat);
        params.add("categorie", categorie);

        return restTemplate.exchange(url, HttpMethod.PUT, null, Map.class, params);
    }

    public ResponseEntity<Map> getCompetitions(int idCompetition) {
        String url = USER_SERVICE_URL + "getCompetitions";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("participants", Boolean.TRUE.toString());

        if (idCompetition != -1) {
            params.add("idCompetition", Integer.toString(idCompetition));
        }

        String urlFinal = UriComponentsBuilder.fromUriString(url)
                .queryParams(params)
                .toUriString();
        return restTemplate.getForEntity(urlFinal, Map.class);
    }

    public ResponseEntity<Map> login(String nomUtilisateur, String mdp) {
        String url = USER_SERVICE_URL + "login";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("nom_utilisateur", nomUtilisateur);
        params.add("mdp", mdp);

        return restTemplate.postForEntity(url, params, Map.class);
    }

    public ResponseEntity<Map> signIn(String nomUtilisateur, String mdp) {
        String url = USER_SERVICE_URL + "signIn";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("nom_utilisateur", nomUtilisateur);
        params.add("mdp", mdp);

        return restTemplate.postForEntity(url, params, Map.class);
    }

    public ResponseEntity<Map> getUtilisateurs(List<Integer> ids) {
        String url = USER_SERVICE_URL + "getUtilisateurs";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        if (!(ids == null || ids.isEmpty())) {
            for (Integer id : ids) {
                params.add("id", id.toString());
            }
        }

        String urlFinal = UriComponentsBuilder.fromUriString(url)
                .queryParams(params)
                .toUriString();

        return restTemplate.getForEntity(urlFinal, Map.class, params);
    }
}
