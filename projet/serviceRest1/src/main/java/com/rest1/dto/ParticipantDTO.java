package com.rest1.dto;

import java.util.List;

public class ParticipantDTO {

    private int id;
    private String nom;
    private List<UtilisateurDTO> votes;

    public ParticipantDTO(int id, String nom, List<UtilisateurDTO> votes) {
        this.id = id;
        this.nom = nom;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<UtilisateurDTO> getVotes() {
        return votes;
    }

    public void setVotes(List<UtilisateurDTO> votes) {
        this.votes = votes;
    }

}
