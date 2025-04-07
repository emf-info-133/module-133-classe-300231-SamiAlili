package com.rest2.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest2.model.Vote;
import com.rest2.model.VoteId;

public interface VoteRepository extends CrudRepository<Vote, VoteId>{
    
}
