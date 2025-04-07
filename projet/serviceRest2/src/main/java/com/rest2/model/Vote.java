package com.rest2.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tr_votes", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"pfk_competition", "pfk_vote"}))
public class Vote {

    @EmbeddedId
    private VoteId id;

    // Getters et Setters
    public VoteId getId() {
        return id;
    }

    public void setId(VoteId id) {
        this.id = id;
    }
}
