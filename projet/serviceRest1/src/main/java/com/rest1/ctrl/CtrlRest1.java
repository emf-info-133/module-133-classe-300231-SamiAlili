package com.rest1.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest1")
public class CtrlRest1 {

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @PostMapping("/ouvrirCompetition")
    public String postCompetition(@RequestBody String entity) {
        return entity;
    }

    @DeleteMapping
    public String deleteCompetition(@RequestBody String body) {
        return body;
    }

    @PutMapping("modifierCompetition/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String body) {
        return body;
    }

    @GetMapping("getCompetitions")
    public List<String> getCompetitions(@RequestParam(name = "idCompetition", defaultValue = "-1") int idCompetition) {
        return null;
    }

    @PostMapping("/login")
    public String postLogin(@RequestBody String entity) {
        return entity;
    }

    @PostMapping("/signIn")
    public String postMethodName(@RequestBody String entity) {
        return entity;
    }

}
