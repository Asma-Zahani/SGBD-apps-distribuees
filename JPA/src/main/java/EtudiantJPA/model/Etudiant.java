package EtudiantJPA.model;

import jakarta.persistence.*;

@Entity
public class Etudiant {

    @Id
    private String matricule;

    private String nom;
    private int age;

    @ManyToOne
    @JoinColumn(name = "noOption")
    private Option option;

    public Etudiant() {
    }

    public Etudiant(String matricule, String nom, int age, Option option) {
        this.matricule = matricule;
        this.nom = nom;
        this.age = age;
        this.option = option;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
