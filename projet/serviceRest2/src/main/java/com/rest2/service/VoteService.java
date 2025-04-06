package com.rest2.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest2.model.Vote;
import com.rest2.repository.UtilisateurRepository;
import com.rest2.repository.VoteRepository;

public class VoteService {
    private final VoteRepository voteRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.voteRepository = voteRepository;
    }

    public String voter(int idCompetition, int idUtilisateur, int idReceveur) {
        Vote newVote = new Vote();
        newVote.setPfkCompetition(idCompetition);
        newVote.setPfkVote(idUtilisateur);
        newVote.setPfkRecoit(idReceveur);
        return "Ajouté";
    }
}
