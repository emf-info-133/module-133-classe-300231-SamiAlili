package com.gw.ctrl;

import com.gw.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gw")
public class Douanier {

    private final UserManager userManager;

    @Autowired
    public Douanier(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping("/ouvrirCompetition")
    public ResponseEntity<String> ouvrirCompetition(@RequestParam String categorie) {
        return userManager.ouvrirCompetition(categorie);
    }

    @DeleteMapping("/supprimerCompetition/{id}")
    public ResponseEntity<String> supprimerCompetition(@PathVariable int id) {
        return userManager.supprimerCompetition(id);
    }

    @PutMapping("/modifierCompetition/{id}")
    public ResponseEntity<String> modifierCompetition(@PathVariable int id,
            @RequestParam String etat,
            @RequestParam String categorie) {
        return userManager.modifierCompetition(id, etat, categorie);
    }

    @GetMapping("/getCompetitions")
    public ResponseEntity<?> getCompetitions(
            @RequestParam(name = "idCompetition", defaultValue = "-1") int idCompetition) {
        return userManager.getCompetitions(idCompetition);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nomUtilisateur,
            @RequestParam String mdp) {
        return userManager.login(nomUtilisateur, mdp);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestParam String nomUtilisateur,
            @RequestParam String mdp) {
        return userManager.signIn(nomUtilisateur, mdp);
    }

    @GetMapping("/getUtilisateurs")
    public ResponseEntity<?> getUtilisateurs(@RequestParam(required = false) List<Integer> ids) {
        return userManager.getUtilisateurs(ids);
    }
}
