package com.rest2.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UtilisateurDTO {
    private String nomUtilisateur;
    private List<String> votes;
    private List<String> participations;

    @Autowired
    public UtilisateurDTO(String nomUtilisateur, List<String> votes, List<String> participations) {
        this.nomUtilisateur = nomUtilisateur;
        this.votes = votes;
        this.participations = participations;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public List<String> getVotes() {
        return votes;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
    }

    public List<String> getParticipations() {
        return participations;
    }

    public void setParticipations(List<String> participations) {
        this.participations = participations;
    }
}
