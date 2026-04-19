package EtudiantJPA.dao;

import jakarta.persistence.*;
import java.util.List;
import EtudiantJPA.model.Etudiant;

public class EtudiantDao {

    EntityManager em = Utilitaire.getEntityManager();
    private static EtudiantDao instance;

    private EtudiantDao(){}

    public static EtudiantDao getInstance(){
        if(instance == null)
            instance = new EtudiantDao();
        return instance;
    }

    public boolean ajouter(Etudiant e){
        em.persist(e);
        return true;
    }

    public boolean supprimer(Etudiant e){
        em.remove(em.contains(e) ? e : em.merge(e));
        return true;
    }

    public void modifier(Etudiant e){
        em.merge(e);
    }

    public Etudiant rechercherById(String matricule){
        return em.find(Etudiant.class, matricule);
    }

    public Etudiant rechercherByNom(String nom){
        try {
            return em.createQuery(
                            "SELECT e FROM Etudiant e WHERE e.nom = :nom", Etudiant.class)
                    .setParameter("nom", nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Etudiant> listerAll(){
        return em.createQuery(
                        "SELECT e FROM Etudiant e", Etudiant.class)
                .getResultList();
    }
}