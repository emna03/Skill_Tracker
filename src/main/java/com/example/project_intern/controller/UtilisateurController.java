package com.example.project_intern.controller;

import com.example.project_intern.model.Parcours;
import com.example.project_intern.model.Utilisateur;
import com.example.project_intern.repository.ParcoursRepository;
import com.example.project_intern.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ParcoursRepository parcoursRepository;

    @GetMapping
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur updated) {
        return utilisateurRepository.findById(id).map(u -> {
            u.setNom(updated.getNom());
            u.setPrenom(updated.getPrenom());
            u.setEmail(updated.getEmail());
            u.setMotDePasse(updated.getMotDePasse());
            u.setTelephone(updated.getTelephone());
            u.setAdresse(updated.getAdresse());
            u.setRole(updated.getRole());
            u.setNiveau(updated.getNiveau());
            u.setObjectifs(updated.getObjectifs());
            u.setHistorique(updated.getHistorique());
            u.setPoste(updated.getPoste());
            u.setParcours(updated.getParcours());
            return utilisateurRepository.save(u);
        }).orElseGet(() -> {
            updated.setId(id);
            return utilisateurRepository.save(updated);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
    }

    @PostMapping("/{userId}/parcours/{parcoursId}")
    public ResponseEntity<String> assignParcoursToUser(@PathVariable Long userId, @PathVariable Long parcoursId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Parcours parcours = parcoursRepository.findById(parcoursId)
                .orElseThrow(() -> new RuntimeException("Parcours non trouvé"));

        if (!utilisateur.getParcours().contains(parcours)) {
            utilisateur.getParcours().add(parcours);
            utilisateurRepository.save(utilisateur);
        }

        return ResponseEntity.ok("Parcours assigné avec succès !");
    }
}
