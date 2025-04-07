package com.rest2.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tr_votes")
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
