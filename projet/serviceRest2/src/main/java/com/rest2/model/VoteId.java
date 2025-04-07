package com.rest2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VoteId {

    @Column(name = "pfk_competition")
    private Integer pfkCompetition;

    @Column(name = "pfk_vote")
    private Integer pfkVote;

    @Column(name = "pfk_recoit")
    private Integer pfkRecoit;

    // Getters et Setters
    public Integer getPfkCompetition() {
        return pfkCompetition;
    }

    public void setPfkCompetition(Integer pfkCompetition) {
        this.pfkCompetition = pfkCompetition;
    }

    public Integer getPfkVote() {
        return pfkVote;
    }

    public void setPfkVote(Integer pfkVote) {
        this.pfkVote = pfkVote;
    }

    public Integer getPfkRecoit() {
        return pfkRecoit;
    }

    public void setPfkRecoit(Integer pfkRecoit) {
        this.pfkRecoit = pfkRecoit;
    }

    // hashCode et equals pour VoteId (nécessaire pour la clé composite)
}
