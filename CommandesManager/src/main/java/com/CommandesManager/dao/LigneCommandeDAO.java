package com.CommandesManager.dao;

import com.CommandesManager.entities.LigneCommande;

import javax.persistence.EntityManager;
import java.util.List;

public class LigneCommandeDAO {

    private static LigneCommandeDAO instance;
    private EntityManager em = Utilitaire.getEntityManager();

    private LigneCommandeDAO() {}

    public static LigneCommandeDAO getInstance() {
        if (instance == null) {
            instance = new LigneCommandeDAO();
        }
        return instance;
    }

    public void ajouter(LigneCommande l) {
        em.persist(l);
    }

    public List<LigneCommande> afficherToutes() {
        return em.createQuery("FROM LigneCommande", LigneCommande.class).getResultList();
    }
}