package com.rest2.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class ParticipationDTO {

    private Integer competition;
    private Integer utilisateur;

    @Autowired
    public ParticipationDTO(Integer competition, Integer utilisateur) {
        this.competition = competition;
        this.utilisateur = utilisateur;
    }

    public Integer getIdCompetition() {
        return competition;
    }

    public void setIdCompetition(Integer competition) {
        this.competition = competition;
    }

    public Integer getNomUtilisateur() {
        return utilisateur;
    }

    public void setNomUtilisateur(Integer utilisateur) {
        this.utilisateur = utilisateur;
    }
}