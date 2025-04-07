package com.rest2.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest2.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Integer>{
    
}
