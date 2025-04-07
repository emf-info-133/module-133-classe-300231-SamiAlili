package com.rest1.dto;

import java.util.List;

public class CompetitionDTO {

    private int id;
    private String etat;
    private String categorie;
    private List<ParticipantDTO> participants;

    public CompetitionDTO(int id, String etat, String categorie, List<ParticipantDTO> participants) {
        this.id = id;
        this.etat = etat;
        this.categorie = categorie;
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

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }
}
