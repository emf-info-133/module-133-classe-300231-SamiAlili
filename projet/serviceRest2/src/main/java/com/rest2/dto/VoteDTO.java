package com.rest2.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class VoteDTO {

    private Integer userVoteur;
    private Integer userReceveur;
    private Integer competition;

    @Autowired
    public VoteDTO(Integer userVoteur, Integer userReceveur, Integer competition) {
        this.userVoteur = userVoteur;
        this.userReceveur = userReceveur;
        this.competition = competition;
    }

    public Integer getUserVoteur() {
        return userVoteur;
    }

    public void setUserVoteur(Integer userVoteur) {
        this.userVoteur = userVoteur;
    }

    public Integer getUserReceveur() {
        return userReceveur;
    }

    public void setUserReceveur(Integer competition) {
        this.competition = competition;
    }

    public Integer getCompetition() {
        return competition;
    }

    public void setCompetition(Integer competition) {
        this.competition = competition;
    }
}