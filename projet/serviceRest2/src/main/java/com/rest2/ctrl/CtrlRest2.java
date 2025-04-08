package com.rest2.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest2.service.ParticipationService;
import com.rest2.service.VoteService;

@RestController
@RequestMapping("/rest2")
public class CtrlRest2 {

    private final ParticipationService participationService;
    private final VoteService voteService;

    @Autowired
    public CtrlRest2(ParticipationService participationService, VoteService voteService) {
        this.participationService = participationService;
        this.voteService = voteService;
    }

    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @GetMapping("/getVotes") // get tous les votes du receveur du vote
    public ResponseEntity<Map<String, Object>> getVotes(@RequestParam int idReceveur, @RequestParam int idCompetition) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", voteService.getVotes(idReceveur, idCompetition));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/voter")
    public ResponseEntity<Map<String, String>> voter(@RequestParam int idCompetition, @RequestParam int idUtilisateur,
            @RequestParam int idReceveur) { // receveur = le user qui reçoit le vote
        Map<String, String> res = new HashMap<>();

        if (voteService.voter(idCompetition, idUtilisateur, idReceveur)) {
            res.put("succès", "Vous avez voté avec succès");
            return ResponseEntity.ok(res);
        } else {
            res.put("erreur", "Erreur lors du vote");
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/getParticipations")
    public ResponseEntity<Map<String, Object>> getParticipations(@RequestParam int idCompetition) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", participationService.getParticipations(idCompetition));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/participer")
    public ResponseEntity<Map<String, String>> participerACompetition(@RequestParam int idUtilisateur,
            @RequestParam int idCompetition) {
        Map<String, String> res = new HashMap<>();
        if (participationService.participer(idUtilisateur, idCompetition)) {
            res.put("succès", "Votre participation a été enregistrée");
            return ResponseEntity.ok(res);
        } else {
            res.put("erreur", "Erreur lors de la participation");
            return ResponseEntity.ok(res);
        }

    }

    @DeleteMapping("/désinscrire")
    public ResponseEntity<Map<String, String>> desinscrire(@RequestParam int idUtilisateur,
            @RequestParam int idCompetition) {
        Map<String, String> res = new HashMap<>();
        if (participationService.desinscrire(idUtilisateur, idCompetition)) {
            res.put("succès", "Vous vous êtes désinscrit avec succès");
            return ResponseEntity.ok(res);
        } else {
            res.put("erreur", "Erreur lors de la désincription");
            return ResponseEntity.ok(res);
        }
    }

    @DeleteMapping("/supprimerCompetition/{idCompetition}")
    public ResponseEntity<Map<String, String>> supprimerCompetition(@PathVariable int idCompetition) {
        Map<String, String> res = new HashMap<>();

        // Supprimer toutes les participations liées à la compétition
        boolean participationsSupprimees = participationService.supprimerParticipationsParCompetition(idCompetition);

        // Supprimer tous les votes liés à la compétition
        boolean votesSupprimes = voteService.supprimerVotesParCompetition(idCompetition);

        if (participationsSupprimees && votesSupprimes) {
            res.put("succès", "La compétition et ses données associées ont été supprimées avec succès");
            return ResponseEntity.ok(res);
        } else {
            res.put("erreur", "Erreur lors de la suppression des données liées à la compétition");
            return ResponseEntity.badRequest().body(res);
        }
    }
}