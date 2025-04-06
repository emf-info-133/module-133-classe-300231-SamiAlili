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

import com.rest1.dto.CompetitionDTO;
import com.rest1.dto.UtilisateurDTO;
import com.rest1.service.CompetitionService;
import com.rest1.service.UtilisateurService;

@RestController
@RequestMapping("/rest1")
public class CtrlRest1 {

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
    public ResponseEntity<String> postCompetition(@RequestParam String categorie) {

        if (categorie == null || categorie.isEmpty()) {
            return ResponseEntity.badRequest().body("les paramèters sont manquants");
        }

        boolean ajout = competitionService.ajouterCompetition(categorie);

        if (!ajout) {
            return ResponseEntity.badRequest().body("Erreur lors de l'ajout");
        }

        return ResponseEntity.ok("OK !");
    }

    @DeleteMapping("/supprimerCompetition/{id}")
    public ResponseEntity<String> deleteCompetition(@PathVariable int id) {

        boolean sup = competitionService.supprimerCompetition(id);

        if (!sup) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression");
        }

        return ResponseEntity.ok("OK!");
    }

    @PutMapping("modifierCompetition/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable int id, @RequestParam String etat,
            @RequestParam String categorie) {

        boolean mod = competitionService.modifierCompetition(id, etat, categorie);

        if (!mod) {
            return ResponseEntity.badRequest().body("Erreur lors de la modification");
        }

        return ResponseEntity.ok("OK !");
    }

    @GetMapping("getCompetitions")
    public ResponseEntity<?> getCompetitions(
            @RequestParam(name = "idCompetition", defaultValue = "-1") int idCompetition) {

        if (idCompetition == -1) {
            return ResponseEntity.ok(competitionService.getCompetitions());
        }

        CompetitionDTO c = competitionService.getCompetitionAvecId(idCompetition);

        return ResponseEntity.ok(c);
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestParam String nom_utilisateur,
            @RequestParam String mdp) {
        if (nom_utilisateur == null || mdp == null || nom_utilisateur.isEmpty() || mdp.isEmpty()) {
            return ResponseEntity.badRequest().body("les paramèters sont manquants");
        }

        UtilisateurDTO u = utilisateurService.login(nom_utilisateur, mdp);

        if (u == null) {
            return ResponseEntity.badRequest().body("Erreur lors de la connexion");
        }

        Map<String, Object> rep = new HashMap<>();
        rep.put("utilisateur", u);
        rep.put("admin", utilisateurService.isAdmin(u.getId()));

        return ResponseEntity.ok(rep);
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> postMethodName(@RequestParam String nom_utilisateur, @RequestParam String mdp) {

        if (nom_utilisateur == null || mdp == null || nom_utilisateur.isEmpty() || mdp.isEmpty()) {
            return ResponseEntity.badRequest().body("les paramèters sont manquants");
        }

        boolean res = utilisateurService.signIn(nom_utilisateur, mdp);

        if (!res) {
            return ResponseEntity.badRequest().body("Erreur lors de l'inscription");
        }

        return ResponseEntity.ok("OK!");
    }

    @GetMapping("/getUtilisateurs")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateurs(
            @RequestParam(defaultValue = "-1") List<Integer> ids) {

        List<UtilisateurDTO> utilisateurs = null;
        if (ids.size() == 1 && ids.get(0) == -1) {
            utilisateurs = utilisateurService.getUtilisateurs();
        } else {
            utilisateurs = utilisateurService.getUtilisateurs(ids.stream().mapToInt(i -> i).toArray());
        }

        return ResponseEntity.ok(utilisateurs);
    }

}
