package com.rest1.dto;

import java.util.List;

public class CompetitionDTO {

    private int id;
    private String etat;
    private String categorie;
    private String nom; // Nouvel attribut
    private List<ParticipantDTO> participants;

    public CompetitionDTO(int id, String etat, String categorie, String nom, List<ParticipantDTO> participants) {
        this.id = id;
        this.etat = etat;
        this.categorie = categorie;
        this.nom = nom;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }
}
