package com.rest1.dto;

import java.util.List;

public class CompetitionDTO {

    private String etat;
    private String categorie;
    private List<String> participants;

    public CompetitionDTO(String etat, String categorie, List<String> participants) {
        this.etat = etat;
        this.categorie = categorie;
        this.participants = participants;
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

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
