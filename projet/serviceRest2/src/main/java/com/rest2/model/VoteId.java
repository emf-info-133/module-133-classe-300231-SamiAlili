package com.rest2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VoteId {

    @Column(name = "pfk_competition")
    private Integer pfkCompetition;

    @Column(name = "pfk_vote")
    private Integer pfkUserVoteur;

    @Column(name = "pfk_recoit")
    private Integer pfkUserReceveur;

    // Getters et Setters
    public Integer getPfkCompetition() {
        return pfkCompetition;
    }

    public void setPfkCompetition(Integer pfkCompetition) {
        this.pfkCompetition = pfkCompetition;
    }

    public Integer getPfkUserVoteur() {
        return pfkUserVoteur;
    }

    public void setPfkUserVoteur(Integer pfkUserVoteur) {
        this.pfkUserVoteur = pfkUserVoteur;
    }

    public Integer getPfkUserReceveur() {
        return pfkUserReceveur;
    }

    public void setPfkUserReceveur(Integer pfkUserReceveur) {
        this.pfkUserReceveur = pfkUserReceveur;
    }

    // hashCode et equals pour VoteId (nécessaire pour la clé composite)
}
