package com.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Departement {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "departement")
    private List<Etudiant> etudiants;

    public Departement() {}

    public Departement(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}