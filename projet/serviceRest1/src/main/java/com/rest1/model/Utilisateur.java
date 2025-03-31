package com.rest1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_utilisateurs")
public class Utilisateur {

    @Id
    @Column(name = "pk_utilisateur", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nom_utilisateur", length = 50)
    private String nomAdmin;
    @Column(name = "mdp", length = 100)
    private String mdp;
    @Column(name = "admin")
    private boolean admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nomAdmin;
    }

    public void setName(String name) {
        this.nomAdmin = name;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdmin() {
        return mdp;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
