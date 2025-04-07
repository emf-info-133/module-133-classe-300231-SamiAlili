package com.rest2.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest2.model.Participation;
import com.rest2.model.ParticipationId;

public interface ParticipationRepository extends CrudRepository<Participation, ParticipationId>{

}
