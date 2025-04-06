package com.rest2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tr_utilisateurs_competitions")
public class Participation {
    
    @Id
    @Column(name = "pfk_competition")
    private Integer pfkCompetition;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pfk_utilisateur")
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
}
