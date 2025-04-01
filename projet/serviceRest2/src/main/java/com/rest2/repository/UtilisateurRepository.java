package com.rest2.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest2.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer>{
    
}
