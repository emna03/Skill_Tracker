package com.example.project_intern.controller;

import com.example.project_intern.model.Utilisateur;
import com.example.project_intern.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    // POST /utilisateurs
    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // PUT /utilisateurs/{id}
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
            return utilisateurRepository.save(u);
        }).orElseGet(() -> {
            updated.setId(id);
            return utilisateurRepository.save(updated);
        });
    }

    // DELETE /utilisateurs/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
    }
}
