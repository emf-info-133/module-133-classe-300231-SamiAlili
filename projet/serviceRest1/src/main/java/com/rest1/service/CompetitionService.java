package com.rest1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest1.dto.CompetitionDTO;
import com.rest1.model.Categorie;
import com.rest1.model.Competition;
import com.rest1.model.Etat;
import com.rest1.repository.CompetitionRepository;

@Service
public class CompetitionService {

    private CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public List<CompetitionDTO> getCompetitions() {

        List<Competition> competitions = (List<Competition>) competitionRepository.findAll();

        if (competitions == null || competitions.isEmpty()) {
            return new ArrayList<>();
        }

        List<CompetitionDTO> competitionsDTO = new ArrayList<>();

        for (Competition c : competitions) {
            competitionsDTO
                    .add(new CompetitionDTO(c.getId(), c.getEtat().toString(), c.getCategorie().toString(),
                            new ArrayList<>()));
        }

        return competitionsDTO;
    }

    public CompetitionDTO getCompetitionAvecId(int id) {

        Competition c = competitionRepository.findById(id).orElse(null);

        if (c == null) {
            return null;
        }

        return new CompetitionDTO(c.getId(), c.getEtat(), c.getCategorie(), new ArrayList<>());
    }

    public boolean ajouterCompetition(String categorie) {

        Competition comp = new Competition();
        comp.setEtat(Etat.INSCRIPTION);
        comp.setCategorie(Categorie.valueOf(categorie.toUpperCase()));

        competitionRepository.save(comp);

        return true;
    }

    public boolean supprimerCompetition(int idCompetition) {

        Competition comp = competitionRepository.findById(idCompetition).orElse(null);

        if (comp == null) {
            return false;
        }

        competitionRepository.delete(comp);

        return true;
    }

    public boolean modifierCompetition(int idCompetition, String etat, String categorie) {

        Competition comp = competitionRepository.findById(idCompetition).orElse(null);

        if (comp == null) {
            return false;
        }

        comp.setEtat(Etat.valueOf(categorie.toUpperCase()));
        comp.setCategorie(Categorie.valueOf(categorie.toUpperCase()));
        competitionRepository.save(comp);

        return true;
    }

}
