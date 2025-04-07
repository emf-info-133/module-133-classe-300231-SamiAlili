package com.rest2.service;

import java.util.ArrayList;
import java.util.List;
import com.rest2.dto.VoteDTO;
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
            newVoteId.setPfkUserVoteur(idUtilisateur);
            newVoteId.setPfkUserReceveur(idReceveur);

            Vote newVote = new Vote();
            newVote.setId(newVoteId);
            voteRepository.save(newVote);

            return true;
        } catch (Exception e) {
            return false;
        }
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
}
