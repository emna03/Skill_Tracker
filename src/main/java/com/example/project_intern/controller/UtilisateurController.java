package com.example.project_intern.controller;

import com.example.project_intern.dto.ApiResponse;
import com.example.project_intern.model.Utilisateur;
import com.example.project_intern.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "*") // permet l'accès depuis Postman ou Angular
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // GET /utilisateurs
    @GetMapping
    public ResponseEntity<ApiResponse<List<Utilisateur>>> getAll() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Liste des utilisateurs récupérée avec succès", utilisateurs)
        );
    }

    // POST /utilisateurs
    @PostMapping
    public ResponseEntity<ApiResponse<Utilisateur>> create(@RequestBody Utilisateur utilisateur) {
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Utilisateur ajouté avec succès", saved)
        );
    }

    // PUT /utilisateurs/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Utilisateur>> update(@PathVariable Long id, @RequestBody Utilisateur updated) {
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
            Utilisateur saved = utilisateurRepository.save(u);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Utilisateur mis à jour", saved)
            );
        }).orElseGet(() -> {
            updated.setId(id);
            Utilisateur saved = utilisateurRepository.save(updated);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Utilisateur créé car introuvable", saved)
            );
        });
    }

    // DELETE /utilisateurs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Utilisateur supprimé avec succès", null)
        );
    }
}
