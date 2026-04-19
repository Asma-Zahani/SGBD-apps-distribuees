package EtudiantJPA.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Option {

    @Id
    private int noOption;

    private String nomOption;

    @OneToMany(mappedBy="option")
    private List<Etudiant> etudiants;

    public Option() {}

    public Option(int noOption, String nomOption) {
        this.noOption = noOption;
        this.nomOption = nomOption;
    }

    public int getNoOption() {
        return noOption;
    }

    public void setNoOption(int noOption) {
        this.noOption = noOption;
    }

    public String getNomOption() {
        return nomOption;
    }

    public void setNomOption(String nomOption) {
        this.nomOption = nomOption;
    }
}