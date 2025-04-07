package com.gw.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gw.manager.UserManager;

import jakarta.servlet.http.HttpSession;

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
    public ResponseEntity<Map> ouvrirCompetition(HttpSession session, @RequestParam String categorie) {
        if (session.getAttribute("user_type") == "admin") {
            Map<String, Object> rep = Map.of("error", "Vous n'avez pas les permission d'ouvrir une compétition");
            return ResponseEntity.badRequest().body(rep);
        }

        return userManager.ouvrirCompetition(categorie);
    }

    @DeleteMapping("/supprimerCompetition/{id}")
    public ResponseEntity<Map> supprimerCompetition(HttpSession session, @PathVariable int id) {
        if (session.getAttribute("user_type") == "admin") {
            Map<String, Object> rep = Map.of("error", "Vous n'avez pas les permissions de supprimer une compétition");
            return ResponseEntity.badRequest().body(rep);
        }

        return userManager.supprimerCompetition(id);
    }

    @PutMapping("/modifierCompetition/{id}")
    public ResponseEntity<Map> modifierCompetition(HttpSession session, @PathVariable int id,
            @RequestParam String etat,
            @RequestParam String categorie) {

        if (session.getAttribute("user_type") == "admin") {
            Map<String, Object> rep = Map.of("error", "Vous n'avez pas les permissions de modifier une compétition");
            return ResponseEntity.badRequest().body(rep);
        }

        return userManager.modifierCompetition(id, etat, categorie);
    }

    @GetMapping("/getCompetitions")
    public ResponseEntity<Map> getCompetitions(
            @RequestParam(name = "idCompetition", defaultValue = "-1") int idCompetition) {
        return userManager.getCompetitions(idCompetition);
    }

    @PostMapping("/login")
    public ResponseEntity<Map> login(HttpSession session, @RequestParam(name = "nom_utilisateur") String nomUtilisateur,
            @RequestParam String mdp) {
        ResponseEntity<Map> response = userManager.login(nomUtilisateur, mdp);

        if (response.getStatusCode().is2xxSuccessful()) {

            Map reponseDTO = response.getBody();

            boolean admin = (boolean) reponseDTO.get("admin");

            session.setAttribute("user_type", admin ? "admin" : "user");

            Map utilisateur = (Map) reponseDTO.get("utilisateur");
            session.setAttribute("user_id", utilisateur.get("id"));
        }

        return response;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Map> signIn(@RequestParam(name = "nom_utilisateur") String nomUtilisateur,
            @RequestParam String mdp) {
        return userManager.signIn(nomUtilisateur, mdp);
    }

    @GetMapping("/getUtilisateurs")
    public ResponseEntity<Map> getUtilisateurs(@RequestParam(required = false) List<Integer> id) {
        return userManager.getUtilisateurs(id);
    }
}
