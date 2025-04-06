package com.rest2.dto;

public class ParticipationDTO {
    private Integer competition;
    private String utilisateur;

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
