package com.rest1.ctrl;

import java.util.HashMap;
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
import org.springframework.web.client.RestTemplate;

import com.rest1.dto.UtilisateurDTO;
import com.rest1.service.CompetitionService;
import com.rest1.service.UtilisateurService;

@RestController
@RequestMapping("/rest1")
public class CtrlRest1 {

    private static final String REST2_UR1 = System.getenv().getOrDefault("REST2_SERVICE_URL",
            "http://localhost:8082/rest2/");

    private final RestTemplate restTemplate;
    private final UtilisateurService utilisateurService;
    private final CompetitionService competitionService;

    @Autowired
    public CtrlRest1(UtilisateurService utilisateurService,
            CompetitionService competitionService, RestTemplate restTemplate) {
        this.utilisateurService = utilisateurService;
        this.competitionService = competitionService;
        this.restTemplate = restTemplate;
    }

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping("/ouvrirCompetition")
    public ResponseEntity<Map<String, String>> postCompetition(@RequestParam String categorie) {
        Map<String, String> rep = new HashMap<>();

        if (categorie == null || categorie.isEmpty()) {
            rep.put("erreur", "les paramèters sont manquants");
            return ResponseEntity.badRequest().body(rep);
        }

        boolean ajout = competitionService.ajouterCompetition(categorie);

        if (!ajout) {
            rep.put("erreur", "Erreur lors de l'ajout");
            return ResponseEntity.badRequest().body(rep);
        }

        rep.put("message", "Ouverture de la compétition réussie");

        return ResponseEntity.ok(rep);
    }

    @DeleteMapping("/supprimerCompetition/{id}")
    public ResponseEntity<Map<String, String>> deleteCompetition(@PathVariable int id) {
        Map<String, String> rep = new HashMap<>();

        boolean sup = competitionService.supprimerCompetition(id);

        if (!sup) {
            rep.put("erreur", "Erreur lors de la suppression");
            return ResponseEntity.badRequest().body(rep);
        }

        rep.put("message", "Suppression de la compétition réussie");

        return ResponseEntity.ok(rep);
    }

    @PutMapping("modifierCompetition/{id}")
    public ResponseEntity<Map<String, String>> putMethodName(@PathVariable int id, @RequestParam String etat,
            @RequestParam String categorie) {

        Map<String, String> rep = new HashMap<>();

        boolean mod = competitionService.modifierCompetition(id, etat, categorie);

        if (!mod) {
            rep.put("erreur", "Erreur lors de la modification");
            return ResponseEntity.badRequest().body(rep);
        }

        return ResponseEntity.ok(rep);
    }

    @GetMapping("getCompetitions")
    public ResponseEntity<Map<String, Object>> getCompetitions(
            @RequestParam(required = false) Integer idCompetition) {

        Map<String, Object> rep = new HashMap<>();

        if (idCompetition == null) {
            rep.put("data", competitionService.getCompetitions());
            return ResponseEntity.ok(rep);
        }

        rep.put("data", competitionService.getCompetitionAvecId(idCompetition.intValue()));

        return ResponseEntity.ok(rep);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> postLogin(@RequestParam String nom_utilisateur,
            @RequestParam String mdp) {

        Map<String, Object> rep = new HashMap<>();

        if (nom_utilisateur == null || mdp == null || nom_utilisateur.isEmpty() || mdp.isEmpty()) {
            rep.put("erreur", "les paramèters sont manquants");
            return ResponseEntity.badRequest().body(rep);
        }

        UtilisateurDTO u = utilisateurService.login(nom_utilisateur, mdp);

        if (u == null) {
            rep.put("erreur", "Erreur lors de la connexion");
            return ResponseEntity.badRequest().body(rep);
        }

        rep.put("utilisateur", u);
        rep.put("admin", utilisateurService.isAdmin(u.getId()));

        return ResponseEntity.ok(rep);
    }

    @PostMapping("/signIn")
    public ResponseEntity<Map<String, String>> postMethodName(
            @RequestParam String nom_utilisateur,
            @RequestParam String mdp) {

        Map<String, String> rep = new HashMap<>();

        if (nom_utilisateur == null || mdp == null || nom_utilisateur.isEmpty() || mdp.isEmpty()) {
            rep.put("erreur", "les paramèters sont manquants");
            return ResponseEntity.badRequest().body(rep);
        }

        boolean res = utilisateurService.signIn(nom_utilisateur, mdp);

        if (!res) {
            rep.put("erreur", "Erreur lors de l'inscription");
            return ResponseEntity.badRequest().body(rep);
        }

        rep.put("message", "Inscription réussie");
        return ResponseEntity.ok(rep);
    }

    @GetMapping("/getUtilisateurs")
    public ResponseEntity<Map<String, Object>> getUtilisateurs(
            @RequestParam(required = false) List<Integer> id) {

        Map<String, Object> rep = new HashMap<>();

        List<UtilisateurDTO> utilisateurs = null;
        if (id == null) {
            utilisateurs = utilisateurService.getUtilisateurs();
        } else {
            utilisateurs = utilisateurService.getUtilisateurs(id.stream().mapToInt(i -> i).toArray());
        }

        if (utilisateurs.size() == 1) {
            rep.put("data", utilisateurs.get(0));
        } else {
            rep.put("data", utilisateurs);
        }

        return ResponseEntity.ok(rep);
    }

}
