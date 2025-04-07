package com.rest2.service;

import com.rest2.model.Vote;
import com.rest2.model.VoteId;
import com.rest2.repository.VoteRepository;

public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public boolean voter(int idCompetition, int idUtilisateur, int idReceveur) {
        try {
            VoteId newVoteId = new VoteId();
            newVoteId.setPfkCompetition(idCompetition);
            newVoteId.setPfkVote(idUtilisateur);
            newVoteId.setPfkRecoit(idReceveur);

            Vote newVote = new Vote();
            newVote.setId(newVoteId);
            voteRepository.save(newVote);
            
            return true;
        } catch (Exception e) {
            return false;
        }

        
    }
}
