package com.rest2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_utilisateur")
public class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_utilisateur")
    private Integer id;
    
    @Column(name = "nom_utilisateur", length = 45)
    private String nomUtilisateur;

    @Column(name = "mdp", length = 100)
    private String mdp;

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nomUtilisateur;
    }

    public void setNom(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
