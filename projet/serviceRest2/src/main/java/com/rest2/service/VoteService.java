package com.rest2.service;

import java.util.List;

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

    public List<Vote> getVotes(int idReceveur, int idCompetition) {
        return voteRepository.findById_PfkRecoitAndId_PfkCompetition(idReceveur, idCompetition);
    }
}
