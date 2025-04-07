package com.rest2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ParticipationId {

    @Column(name = "pfk_competition")
    private Integer pfkCompetition;

    @Column(name = "pfk_utilisateur")
    private Integer pfkUtilisateur;

    // Getters et Setters
    public Integer getPfkCompetition() {
        return pfkCompetition;
    }

    public void setPfkCompetition(Integer pfkCompetition) {
        this.pfkCompetition = pfkCompetition;
    }

    public Integer getPfkUtilisateur() {
        return pfkUtilisateur;
    }

    public void setPfkUtilisateur(Integer pfkUtilisateur) {
        this.pfkUtilisateur = pfkUtilisateur;
    }

    // hashCode et equals pour ParticipationId
}
