package com.entities;

import javax.persistence.*;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private double moyenne;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Etudiant() {}

    public Etudiant(Long id, String nom, double moyenne, Departement d) {
        this.id = id;
        this.nom = nom;
        this.moyenne = moyenne;
        this.departement = d;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}