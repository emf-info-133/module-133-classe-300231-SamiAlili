package com.rest2.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest2.service.ParticipationService;
import com.rest2.service.VoteService;

import org.springframework.web.bind.annotation.GetMapping;


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

    @PostMapping("/participer")
    public ResponseEntity<String> participerACompetition(@RequestParam int idUtilisateur,
            @RequestParam int idCompetition) {
        if (participationService.participer(idUtilisateur, idCompetition)) {
            return ResponseEntity.ok("Participation réussie");
        } else {
            return ResponseEntity.ok("Participation echouée");
        }
        
    }

    @PostMapping("/voter")
    public ResponseEntity<String> voter(@RequestParam int idCompetition, @RequestParam int idUtilisateur,
            @RequestParam int idReceveur) { // receveur = le user qui reçoit le vote
        if (voteService.voter(idCompetition, idUtilisateur, idReceveur)) {
            return ResponseEntity.ok("Vous avez voté");
        } else {
            return ResponseEntity.ok("Une erreur s'est produite");
        }
        
    }
    
    @DeleteMapping("/désinscrire")
    public ResponseEntity<String> desinscrire(@RequestParam int idUtilisateur, @RequestParam int idCompetition) {
        if (participationService.desinscrire(idUtilisateur, idCompetition)) {
            return ResponseEntity.ok("Désinscription réussie");
        } else {
            return ResponseEntity.ok("Désinscription echouée");
        }
        
    }
    
}
