package com.rest2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tr_votes")
public class Vote {

    @Id
    @Column(name = "pfk_competition")
    private Integer pfkCompetition;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pfk_vote")
    private Integer pfkVote;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pfk_recoit")
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
}
