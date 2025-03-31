package com.rest1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest1.dto.UtilisateurDTO;
import com.rest1.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurDTO login(String nomUtilisateur, String mdp) {
        return null;
    }

    public boolean signIn(String nomUtilisateur, String mdp) {
        return false;
    }

}
