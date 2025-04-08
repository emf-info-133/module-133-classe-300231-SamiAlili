package com.rest2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest2.dto.ParticipationDTO;
import com.rest2.model.Participation;
import com.rest2.model.ParticipationId;
import com.rest2.repository.ParticipationRepository;

@Service
public class ParticipationService {

    private static final String REST1_URL = System.getenv().getOrDefault("REST1_SERVICE_URL",
            "http://localhost:8081/rest1/");
    private final ParticipationRepository participationRepository;
    private final RestTemplate restTemplate;

    public ParticipationService(ParticipationRepository participationRepository, RestTemplate restTemplate) {
        this.participationRepository = participationRepository;
        this.restTemplate = restTemplate;
    }

    public boolean participer(int idUtilisateur, int idCompetition) {

        String url1 = UriComponentsBuilder.fromUriString(REST1_URL + "getCompetitions")
                .queryParam("idCompetition", idCompetition)
                .toUriString();
        String url2 = UriComponentsBuilder.fromUriString(REST1_URL + "getUtilisateurs")
                .queryParam("id", idUtilisateur)
                .toUriString();

        ResponseEntity<Map> response1 = restTemplate.getForEntity(url1, Map.class);
        ResponseEntity<Map> response2 = restTemplate.getForEntity(url2, Map.class);


        if (response1.getStatusCode().is2xxSuccessful() && response2.getStatusCode().is2xxSuccessful()) {
            Map responseBody1 = response1.getBody();
            Map responseBody2 = response2.getBody();
            if (responseBody1.get("data") != null && responseBody2.get("data") != null) {

                ParticipationId newParticipationId = new ParticipationId();
                newParticipationId.setPfkUtilisateur(idUtilisateur);
                newParticipationId.setPfkCompetition(idCompetition);

                Participation newParticipation = new Participation();
                newParticipation.setId(newParticipationId);
                participationRepository.save(newParticipation);

                return true;
            } else {
                return false; // Si l'API renvoie une erreur ou un statut différent

            }
        } else {
            return false; // Si l'API retourne une erreur (code autre que 2xx)
        }
    }

    public boolean desinscrire(int idUtilisateur, int idCompetition) {
        String url1 = UriComponentsBuilder.fromUriString(REST1_URL + "getCompetitions")
                .queryParam("idCompetition", idCompetition)
                .toUriString();
        String url2 = UriComponentsBuilder.fromUriString(REST1_URL + "getUtilisateurs")
                .queryParam("id", idUtilisateur)
                .toUriString();

        ResponseEntity<Map> response1 = restTemplate.getForEntity(url1, Map.class);
        ResponseEntity<Map> response2 = restTemplate.getForEntity(url2, Map.class);

        if (response1.getStatusCode().is2xxSuccessful() && response2.getStatusCode().is2xxSuccessful()) {
            Map responseBody1 = response1.getBody();
            Map responseBody2 = response2.getBody();
            if (responseBody1.get("data") != null && responseBody2.get("data") != null) {
                ParticipationId participationId = new ParticipationId();
                participationId.setPfkUtilisateur(idUtilisateur);
                participationId.setPfkCompetition(idCompetition);

                // Vérifier si l'entité existe avant de la supprimer
                if (participationRepository.existsById(participationId)) {
                    participationRepository.deleteById(participationId);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false; // Si l'API renvoie une erreur ou un statut différent

            }
        } else {
            return false; // Si l'API retourne une erreur (code autre que 2xx)
        }
    }

    public List<ParticipationDTO> getParticipations(int idCompetition) {

        List<Participation> participations = participationRepository.findById_PfkCompetition(idCompetition);
        List<ParticipationDTO> participationDTOs = new ArrayList<>();

        for (Participation participation : participations) {
            ParticipationDTO participationDTO = new ParticipationDTO(participation.getId().getPfkCompetition(),
                    participation.getId().getPfkUtilisateur());
            participationDTOs.add(participationDTO);
        }
        return participationDTOs;
    }

    public boolean supprimerParticipationsParCompetition(int idCompetition) {
        try {
            participationRepository.deleteById_PfkCompetition(idCompetition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
