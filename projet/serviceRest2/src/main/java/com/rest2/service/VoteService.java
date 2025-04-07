package com.rest2.service;

import com.rest2.model.Vote;
import com.rest2.repository.VoteRepository;

public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public String voter(int idCompetition, int idUtilisateur, int idReceveur) {
        Vote newVote = new Vote();
        newVote.setPfkCompetition(idCompetition);
        newVote.setPfkVote(idUtilisateur);
        newVote.setPfkRecoit(idReceveur);
        voteRepository.save(newVote);
        return "Ajouté";
    }
}
