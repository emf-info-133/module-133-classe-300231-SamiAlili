package com.rest1.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest1.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

    Utilisateur findByNomUtilisateur(String nomUtilisateur);
}
