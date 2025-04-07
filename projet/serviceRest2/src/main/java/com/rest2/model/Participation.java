package com.rest2.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tr_utilisateurs_competitions", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"pfk_competition", "pfk_utilisateur"}))
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