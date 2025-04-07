package com.rest2.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class ParticipationDTO {
    private Integer competition;
    private String utilisateur;

    @Autowired
    public ParticipationDTO(Integer competition, String utilisateur) {
        this.competition = competition;
        this.utilisateur = utilisateur;
    }

    public Integer getIdCompetition() {
        return competition;
    }

    public void setIdCompetition(Integer competition) {
        this.competition = competition;
    }

    public String getNomUtilisateur() {
        return utilisateur;
    }

    public void setNomUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
}
