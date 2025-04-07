package com.rest2.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tr_utilisateurs_competitions")
public class Participation {

    @EmbeddedId
    private ParticipationId id;

    // Getters et Setters
    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }
}
