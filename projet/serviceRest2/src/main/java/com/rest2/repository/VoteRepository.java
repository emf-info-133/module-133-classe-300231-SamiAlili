package com.rest2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest2.model.Vote;
import com.rest2.model.VoteId;

public interface VoteRepository extends CrudRepository<Vote, VoteId>{
    
    List<Vote> findById_PfkRecoitAndId_PfkCompetition(int idReceveur, int idCompetition);
}
