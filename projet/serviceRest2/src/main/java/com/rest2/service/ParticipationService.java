package com.rest2.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest2.model.Participation;
import com.rest2.repository.ParticipationRepository;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final RestTemplate restTemplate;

    public ParticipationService(ParticipationRepository participationRepository, RestTemplate restTemplate) {
        this.participationRepository = participationRepository;
        this.restTemplate = restTemplate;
    }

    public String participer(int idUtilisateur, int idCompetition) {

        String url = UriComponentsBuilder.fromUriString("http://localhost:8081/rest1/getCompetitions")
                                        .queryParam("idCompetition", idCompetition)
                                        .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {

                Participation newParticipation = new Participation();
                newParticipation.setPfkUtilisateur(idUtilisateur);
                newParticipation.setPfkCompetition(idCompetition);
                participationRepository.save(newParticipation);

                return "Ajouté";
            } else {
                return "Erreur"; // Si l'API renvoie une erreur ou un statut différent
        
            }
        } else {
            return "Erreur"; // Si l'API retourne une erreur (code autre que 2xx)
        }

        /** 
        Competition competition = competitionRepository.findById(idCompetition).orElse(null);
        if (competition == null) {
        return "competition not found";
        }
        Participation newParticipation = new Participation();
        newParticipation.setPfkUtilisateur(idUtilisateur);
        newParticipation.setPfkCompetition(idCompetition);
        participationRepository.save(newParticipation);
        return "Ajouté";
        */ 
    }

    public String desinscrire(int idUtilisateur, int idCompetition) {
        Participation participation = new Participation();
        participation.setPfkUtilisateur(idUtilisateur);
        participation.setPfkCompetition(idCompetition);
        participationRepository.delete(participation);
        return "Désinscrit";
    }
}
