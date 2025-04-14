package com.gw.manager;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CompetitionManager {

    private final String COMPETITION_SERVICE_URL;

    private final RestTemplate restTemplate;

    public CompetitionManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        COMPETITION_SERVICE_URL = System.getenv().getOrDefault("REST2_SERVICE_URL", "http://localhost:8082/rest2/");
    }

    public ResponseEntity<String> getVotes(int idReceveur, int idCompetition) {
        String url = COMPETITION_SERVICE_URL + "getVotes";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("idReceveur", Integer.toString(idReceveur));
        params.add("idCompetition", Integer.toString(idCompetition));

        String urlFinal = UriComponentsBuilder.fromUriString(url)
                .queryParams(params)
                .toUriString();

        return restTemplate.getForEntity(urlFinal, String.class);
    }

    public ResponseEntity<Map> voter(int idCompetition, int idUtilisateur, int idReceveur) {
        String url = COMPETITION_SERVICE_URL + "voter";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("idCompetition", Integer.toString(idCompetition));
        params.add("idUtilisateur", Integer.toString(idUtilisateur));
        params.add("idReceveur", Integer.toString(idReceveur));

        return restTemplate.postForEntity(url, params, Map.class);
    }

    public ResponseEntity<String> getParticipations(int idCompetition) {
        String url = COMPETITION_SERVICE_URL + "getParticipations";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("idCompetition", Integer.toString(idCompetition));

        String urlFinal = UriComponentsBuilder.fromUriString(url)
                .queryParams(params)
                .toUriString();

        return restTemplate.getForEntity(urlFinal, String.class);
    }

    public ResponseEntity<String> participerACompetition(int idUtilisateur, int idCompetition) {
        String url = COMPETITION_SERVICE_URL + "participer";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("idUtilisateur", Integer.toString(idUtilisateur));
        params.add("idCompetition", Integer.toString(idCompetition));

        return restTemplate.postForEntity(url, params, String.class);
    }

    public ResponseEntity<String> desinscrire(int idUtilisateur, int idCompetition) {
        String url = COMPETITION_SERVICE_URL + "desinscrire";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("idUtilisateur", Integer.toString(idUtilisateur));
        params.add("idCompetition", Integer.toString(idCompetition));

        String urlFinal = UriComponentsBuilder.fromUriString(url)
                .queryParams(params)
                .toUriString();

        return restTemplate.exchange(urlFinal, HttpMethod.DELETE, null, String.class);
    }

}
