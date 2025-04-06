package com.rest2.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest2.model.Participation;
import com.rest2.repository.ParticipationRepository;
import com.rest2.repository.UtilisateurRepository;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository, UtilisateurRepository utilisateurRepository) {
        this.participationRepository = participationRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public String participer(int idUtilisateur, int idCompetition) {
        //Competition competition = competitionRepository.findById(idCompetition).orElse(null);
        //if (competition == null) {
        //    return "competition not found";
        //}
        Participation newParticipation = new Participation();
        newParticipation.setPfkUtilisateur(idUtilisateur);
        newParticipation.setPfkCompetition(idCompetition);
        participationRepository.save(newParticipation);
        return "Ajouté";
    }

    public String desinscrire(int idUtilisateur, int idCompetition) {
        Participation participation = new Participation();
        participation.setPfkUtilisateur(idUtilisateur);
        participation.setPfkCompetition(idCompetition);
        participationRepository.delete(participation);
        return "Désinscrit";
    }
}
