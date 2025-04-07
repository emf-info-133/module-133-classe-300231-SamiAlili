package com.rest2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest2.model.Participation;
import com.rest2.model.ParticipationId;

public interface ParticipationRepository extends CrudRepository<Participation, ParticipationId> {

    public List<Participation> findById_PfkCompetition(int idCompetition);

}
