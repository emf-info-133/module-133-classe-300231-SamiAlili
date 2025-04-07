package com.rest1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest1.dto.UtilisateurDTO;
import com.rest1.model.Utilisateur;
import com.rest1.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public UtilisateurDTO login(String nomUtilisateur, String mdp) {

        Utilisateur u = utilisateurRepository.findByNomUtilisateur(nomUtilisateur);

        // vérifie l'existance de l'utilisateur
        if (u == null) {
            return null;
        }

        // vérifie le mot de passe
        if (!passwordEncoder.matches(mdp, u.getMdp())) {
            return null;
        }

        UtilisateurDTO uDto = new UtilisateurDTO(u.getId(), u.getNomUtilisateur());

        return uDto;

    }

    public boolean isAdmin(int id) {
        Utilisateur adm = utilisateurRepository.findById(id).orElse(null);

        if (adm == null) {
            return false;
        }

        return adm.isAdmin();
    }

    public List<UtilisateurDTO> getUtilisateurs(int... ids) {

        List<Utilisateur> utilisateurs = null;
        if (ids.length == 0) {
            utilisateurs = (List<Utilisateur>) utilisateurRepository.findAll();
        } else {

            utilisateurs = new ArrayList<>();

            for (int id : ids) {
                Utilisateur u = utilisateurRepository.findById(id).orElse(null);

                if (u != null) {
                    utilisateurs.add(u);
                }
            }
        }

        if (utilisateurs == null || utilisateurs.isEmpty()) {
            return new ArrayList<>();
        }

        List<UtilisateurDTO> utilisateursDTO = new ArrayList<>();
        for (Utilisateur u : utilisateurs) {
            utilisateursDTO.add(new UtilisateurDTO(u.getId(), u.getNomUtilisateur()));
        }

        return utilisateursDTO;
    }

    public boolean signIn(String nomUtilisateur, String mdp) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(nomUtilisateur);
        utilisateur.setMdp(passwordEncoder.encode(mdp));
        utilisateur.setAdmin(false);

        try {
            utilisateurRepository.save(utilisateur);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
