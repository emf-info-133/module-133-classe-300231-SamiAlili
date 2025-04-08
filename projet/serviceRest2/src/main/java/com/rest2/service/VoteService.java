package com.rest2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest2.dto.VoteDTO;
import com.rest2.model.Vote;
import com.rest2.model.VoteId;
import com.rest2.repository.VoteRepository;

public class VoteService {
    private final String REST1_URL;
    private final VoteRepository voteRepository;
    private final RestTemplate restTemplate;

    public VoteService(VoteRepository voteRepository, RestTemplate restTemplate) {
        this.voteRepository = voteRepository;
        this.restTemplate = restTemplate;

        REST1_URL = System.getenv().getOrDefault("REST1_SERVICE_URL", "http://localhost:8081/rest1/");
    }

    public boolean voter(int idCompetition, int idUtilisateur, int idReceveur) {
        String url1 = UriComponentsBuilder.fromUriString(REST1_URL + "getCompetitions")
                .queryParam("idCompetition", idCompetition)
                .toUriString();
        String url2 = UriComponentsBuilder.fromUriString(REST1_URL + "getUtilisateurs")
                .queryParam("id", idUtilisateur)
                .queryParam("id", idReceveur)
                .toUriString();

        ResponseEntity<Map> response1 = restTemplate.getForEntity(url1, Map.class);
        ResponseEntity<Map> response2 = restTemplate.getForEntity(url2, Map.class);

        if (response1.getStatusCode().is2xxSuccessful() && response2.getStatusCode().is2xxSuccessful()) {
            Map responseBody1 = response1.getBody();
            Map responseBody2 = response2.getBody();
            List responseList1 = (List) responseBody2.get("data");
            if (responseBody1.get("data") != null && responseList1.size() == 2) {
                // Les votes sont autorisés uniquement lors de l'état "votation" d'une
                // compétition
                String etat = ((Map<String, String>) responseBody1.get("data")).get("etat").toLowerCase();
                if ("votation".equals(etat)) {
                    VoteId newVoteId = new VoteId();
                    newVoteId.setPfkCompetition(idCompetition);
                    newVoteId.setPfkUserVoteur(idUtilisateur);
                    newVoteId.setPfkUserReceveur(idReceveur);

                    Vote newVote = new Vote();
                    newVote.setId(newVoteId);
                    voteRepository.save(newVote);

                    return true;
                }
            }
        }
        return false;
    }

    public List<VoteDTO> getVotes(int idReceveur, int idCompetition) {
        List<Vote> votes = voteRepository.findById_PfkUserReceveurAndId_PfkCompetition(idReceveur, idCompetition);

        List<VoteDTO> voteDTOs = new ArrayList<>();

        for (Vote vote : votes) {
            VoteDTO voteDTO = new VoteDTO(vote.getId().getPfkUserVoteur(), vote.getId().getPfkUserReceveur(),
                    vote.getId().getPfkCompetition());
            voteDTOs.add(voteDTO);
        }
        return voteDTOs;
    }

    @Transactional
    public boolean supprimerVotesParCompetition(int idCompetition) {
        try {
            voteRepository.deleteById_PfkCompetition(idCompetition);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
