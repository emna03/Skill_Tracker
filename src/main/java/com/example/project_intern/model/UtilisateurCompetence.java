package com.example.project_intern.model;

import jakarta.persistence.*;

@Entity
public class UtilisateurCompetence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Competence competence;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    public UtilisateurCompetence() {}

    public UtilisateurCompetence(Utilisateur utilisateur, Competence competence, Niveau niveau) {
        this.utilisateur = utilisateur;
        this.competence = competence;
        this.niveau = niveau;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Competence getCompetence() { return competence; }
    public void setCompetence(Competence competence) { this.competence = competence; }

    public Niveau getNiveau() { return niveau; }
    public void setNiveau(Niveau niveau) { this.niveau = niveau; }
}
