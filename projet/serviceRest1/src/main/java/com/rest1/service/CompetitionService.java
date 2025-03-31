package com.rest1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest1.dto.CompetitionDTO;
import com.rest1.repository.CompetitionRepository;

@Service
public class CompetitionService {

    private CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<CompetitionDTO> getCompetitions() {
        return null;
    }

    public CompetitionDTO getCompetitionAvecId(int id) {
        return null;
    }

    public boolean ajouterCompetition(String etat, String categorie) {
        return false;
    }

    public boolean supprimerCompetition(int idCompetition) {
        return false;
    }

    public boolean modifierCompetition(int idCompetition, String etat, String categorie) {
        return false;
    }

}
