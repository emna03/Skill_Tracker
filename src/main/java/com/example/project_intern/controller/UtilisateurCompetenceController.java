package com.example.project_intern.controller;

import com.example.project_intern.model.*;
import com.example.project_intern.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur-competences")
@CrossOrigin("*")
public class UtilisateurCompetenceController {

    @Autowired
    private UtilisateurCompetenceRepository utilisateurCompetenceRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    // GET : Liste de toutes les compétences des utilisateurs
    @GetMapping
    public List<UtilisateurCompetence> getAll() {
        return utilisateurCompetenceRepository.findAll();
    }

    // POST : Assigner une compétence à un utilisateur avec un niveau
    @PostMapping("/{userId}/{competenceId}")
    public ResponseEntity<String> assignCompetenceToUser(
            @PathVariable Long userId,
            @PathVariable Long competenceId,
            @RequestParam Niveau niveau
    ) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Competence competence = competenceRepository.findById(competenceId)
                .orElseThrow(() -> new RuntimeException("Compétence non trouvée"));

        UtilisateurCompetence uc = new UtilisateurCompetence(utilisateur, competence, niveau);
        utilisateurCompetenceRepository.save(uc);

        return ResponseEntity.ok("Compétence assignée avec succès !");
    }

    // PUT : Mettre à jour le niveau d’une compétence pour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurCompetence> updateNiveau(
            @PathVariable Long id,
            @RequestParam Niveau niveau
    ) {
        UtilisateurCompetence uc = utilisateurCompetenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association non trouvée"));

        uc.setNiveau(niveau);
        return ResponseEntity.ok(utilisateurCompetenceRepository.save(uc));
    }

    // DELETE : Supprimer une compétence d’un utilisateur
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurCompetenceRepository.deleteById(id);
    }
}
