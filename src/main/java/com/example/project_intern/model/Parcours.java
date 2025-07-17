package com.example.project_intern.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Parcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @ManyToMany
    private List<Competence> competences;

    public Parcours() {}

    public Parcours(String nom, String description, List<Competence> competences) {
        this.nom = nom;
        this.description = description;
        this.competences = competences;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Competence> getCompetences() { return competences; }
    public void setCompetences(List<Competence> competences) { this.competences = competences; }
}
