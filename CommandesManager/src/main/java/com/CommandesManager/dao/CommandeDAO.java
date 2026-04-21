package com.CommandesManager.dao;

import com.CommandesManager.entities.Commande;

import javax.persistence.EntityManager;
import java.util.List;

public class CommandeDAO {

    private static CommandeDAO instance;
    private EntityManager em = Utilitaire.getEntityManager();

    private CommandeDAO() {}

    public static CommandeDAO getInstance() {
        if (instance == null) {
            instance = new CommandeDAO();
        }
        return instance;
    }

    public void ajouter(Commande c) {
        em.persist(c);
    }

    public Commande trouver(Long id) {
        return em.find(Commande.class, id);
    }

    public List<Commande> afficherToutes() {
        return em.createQuery("FROM Commande", Commande.class).getResultList();
    }

    public void supprimer(Commande c) {
        em.remove(em.contains(c) ? c : em.merge(c));
    }
}