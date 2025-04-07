package com.rest1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_competitions")
public class Competition {

    @Id
    @Column(name = "pk_competition", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "etat", length = 45)
    private Etat etat;
    @Enumerated(EnumType.STRING)
    @Column(name = "categorie", length = 45)
    private Categorie categorie;

    @Column(name = "nom", length = 100)
    private String nom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtat() {
        return etat.toString().toLowerCase();
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getCategorie() {
        return categorie.toString().toLowerCase();
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
