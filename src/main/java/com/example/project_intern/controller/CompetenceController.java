package com.example.project_intern.controller;

import com.example.project_intern.model.Competence;
import com.example.project_intern.repository.CompetenceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
@CrossOrigin("*")
public class CompetenceController {

    private final CompetenceRepository competenceRepository;

    public CompetenceController(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @GetMapping
    public List<Competence> getAll() {
        return competenceRepository.findAll();
    }

    @PostMapping
    public Competence create(@RequestBody Competence competence) {
        return competenceRepository.save(competence);
    }

    @PutMapping("/{id}")
    public Competence update(@PathVariable Long id, @RequestBody Competence updated) {
        return competenceRepository.findById(id).map(c -> {
            c.setNom(updated.getNom());
            c.setDescription(updated.getDescription());
            c.setCategorie(updated.getCategorie());
            return competenceRepository.save(c);
        }).orElseGet(() -> {
            updated.setId(id);
            return competenceRepository.save(updated);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        competenceRepository.deleteById(id);
    }
}
