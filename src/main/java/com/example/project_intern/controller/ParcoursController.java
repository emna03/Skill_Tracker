package com.example.project_intern.controller;

import com.example.project_intern.model.Parcours;
import com.example.project_intern.repository.ParcoursRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcours")
@CrossOrigin("*")
public class ParcoursController {

    private final ParcoursRepository parcoursRepository;

    public ParcoursController(ParcoursRepository parcoursRepository) {
        this.parcoursRepository = parcoursRepository;
    }

    @GetMapping
    public List<Parcours> getAll() {
        return parcoursRepository.findAll();
    }

    @PostMapping
    public Parcours create(@RequestBody Parcours parcours) {
        return parcoursRepository.save(parcours);
    }

    @PutMapping("/{id}")
    public Parcours update(@PathVariable Long id, @RequestBody Parcours updated) {
        return parcoursRepository.findById(id).map(p -> {
            p.setNom(updated.getNom());
            p.setDescription(updated.getDescription());
            p.setCompetences(updated.getCompetences());
            return parcoursRepository.save(p);
        }).orElseGet(() -> {
            updated.setId(id);
            return parcoursRepository.save(updated);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parcoursRepository.deleteById(id);
    }
}
