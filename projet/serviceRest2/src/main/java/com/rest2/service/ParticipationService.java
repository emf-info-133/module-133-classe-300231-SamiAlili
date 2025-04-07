package com.rest2.service;

import java.util.ArrayList;
import java.util.List;

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
    private final ParticipationRepository participationRepository;
    private final RestTemplate restTemplate;

    public ParticipationService(ParticipationRepository participationRepository, RestTemplate restTemplate) {
        this.participationRepository = participationRepository;
        this.restTemplate = restTemplate;
    }

    public boolean participer(int idUtilisateur, int idCompetition) {

        String url = UriComponentsBuilder.fromUriString("http://localhost:8081/rest1/getCompetitions")
                                        .queryParam("idCompetition", idCompetition)
                                        .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null) {

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
    }

    public List<ParticipationDTO> getParticipations(int idCompetition) {
        List<Participation> participations = participationRepository.findById_PfkCompetition(idCompetition);
        List<ParticipationDTO> participationDTOs = new ArrayList<>();

        for (Participation participation : participations) {
            ParticipationDTO participationDTO = new ParticipationDTO(participation.getId().getPfkCompetition(), participation.getId().getPfkUtilisateur());
            participationDTOs.add(participationDTO);
        }
        return participationDTOs;
    }
}
