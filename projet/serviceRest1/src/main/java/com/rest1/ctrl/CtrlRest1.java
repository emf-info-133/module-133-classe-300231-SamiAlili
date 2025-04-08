package com.rest1.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest1.dto.CompetitionDTO;
import com.rest1.dto.ParticipantDTO;
import com.rest1.dto.UtilisateurDTO;
import com.rest1.service.CompetitionService;
import com.rest1.service.UtilisateurService;

@RestController
@RequestMapping("/rest1")
public class CtrlRest1 {

    private static final String REST2_URL = System.getenv().getOrDefault("REST2_SERVICE_URL",
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
    public ResponseEntity<Map<String, String>> postCompetition(@RequestParam String categorie,
            @RequestParam String nom) {
        Map<String, String> rep = new HashMap<>();

        if (categorie == null || categorie.isEmpty() || nom == null || nom.isEmpty()) {
            rep.put("erreur", "Les paramètres sont manquants");
            return ResponseEntity.badRequest().body(rep);
        }

        boolean ajout = competitionService.ajouterCompetition(categorie, nom);

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

        // supprime toutes les participations et les votes associés à la compétition
        String url = REST2_URL + "supprimerCompetition/" + id;

        if (restTemplate.exchange(url, HttpMethod.DELETE, null, String.class).getStatusCode().isError()) {
            rep.put("erreur", "Erreur lors de la suppression des participations et des votes");
            return ResponseEntity.badRequest().body(rep);
        }

        // supprime la compétition
        boolean sup = competitionService.supprimerCompetition(id);

        if (!sup) {
            rep.put("erreur", "Erreur lors de la suppression");
            return ResponseEntity.badRequest().body(rep);
        }

        rep.put("message", "Suppression de la compétition réussie");

        return ResponseEntity.ok(rep);
    }

    @PutMapping("/modifierCompetition/{id}")
    public ResponseEntity<Map<String, String>> putMethodName(@PathVariable int id, @RequestParam String etat,
            @RequestParam String categorie, @RequestParam String nom) {

        Map<String, String> rep = new HashMap<>();

        boolean mod = competitionService.modifierCompetition(id, etat, categorie, nom);

        if (!mod) {
            rep.put("erreur", "Erreur lors de la modification");
            return ResponseEntity.badRequest().body(rep);
        }

        rep.put("message", "Modification réussie");
        return ResponseEntity.ok(rep);
    }

    @GetMapping("/getCompetitions")
    public ResponseEntity<Map<String, Object>> getCompetitions(
            @RequestParam(required = false) Integer idCompetition,
            @RequestParam(required = false, defaultValue = "false") boolean participants) {

        if (idCompetition == null) {
            List<CompetitionDTO> comps = competitionService.getCompetitions();

            if (!comps.isEmpty() && participants) {
                comps.forEach((comp) -> addParticipantsCompetition(comp));
            }

            return ResponseEntity.ok(Map.<String, Object>of("data", comps));
        }

        CompetitionDTO comp = competitionService.getCompetitionAvecId(idCompetition.intValue());

        if (comp != null && participants) {
            addParticipantsCompetition(comp);
        }

        Map<String, Object> rep = new HashMap<>();
        rep.put("data", comp);

        return ResponseEntity.ok(rep);
    }

    private CompetitionDTO addParticipantsCompetition(CompetitionDTO comp) {

        // fait la requête pour récupérer la liste des participants de la compétition
        MultiValueMap<String, String> paramsParticipations = new LinkedMultiValueMap<>();
        paramsParticipations.add("idCompetition", Integer.toString(comp.getId()));

        String urlGetParticipations = UriComponentsBuilder.fromUriString(REST2_URL + "getParticipations")
                .queryParams(paramsParticipations)
                .toUriString();

        ResponseEntity<Map> reponseParticipations = restTemplate.getForEntity(urlGetParticipations, Map.class);

        // si la requête est OK
        if (reponseParticipations.getStatusCode().is2xxSuccessful()) {

            // récupère la liste des participations
            List participations = (List) reponseParticipations.getBody().get("data");

            // si la liste n'est pas vide
            if (participations != null && !participations.isEmpty()) {

                List<ParticipantDTO> partDTOs = new ArrayList<>();

                // itère chaque participants de la compétition
                for (Map participation : (List<Map>) participations) {

                    // récupère l'id du participant
                    int idParticipant = (int) participation.get("nomUtilisateur");

                    // récupère l'id du participant
                    UtilisateurDTO part = utilisateurService.getUtilisateurs(new int[] { idParticipant }).get(0);

                    // si le participant n'est pas null
                    if (part != null) {

                        MultiValueMap<String, String> paramsVotes = new LinkedMultiValueMap<>();
                        paramsVotes.add("idReceveur", Integer.toString(idParticipant));
                        paramsVotes.add("idCompetition", Integer.toString(comp.getId()));

                        String urlGetVotes = UriComponentsBuilder.fromUriString(REST2_URL + "getVotes")
                                .queryParams(paramsVotes)
                                .toUriString();

                        // fait une requête pour récupérer les votes pour ce participant
                        ResponseEntity<Map> reponseVotes = restTemplate.getForEntity(urlGetVotes,
                                Map.class);

                        if (reponseVotes.getStatusCode().is2xxSuccessful()) {
                            List votes = (List) reponseVotes.getBody().get("data");
                            if (votes != null && !votes.isEmpty()) {
                                List<UtilisateurDTO> voteurs = new ArrayList<>();
                                for (Map vote : (List<Map>) votes) {
                                    int idVoteur = (int) vote.get("userVoteur");

                                    UtilisateurDTO voteur = utilisateurService.getUtilisateurs(new int[] { idVoteur })
                                            .get(0);

                                    if (voteur != null) {
                                        voteurs.add(voteur);
                                    }

                                    voteurs.add(new UtilisateurDTO(voteur.getId(), voteur.getNom()));
                                }

                                partDTOs.add(new ParticipantDTO(idParticipant, part.getNom(), voteurs));

                            }
                        }
                    }
                }

                comp.setParticipants(partDTOs);

            }
        }

        return comp;
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
