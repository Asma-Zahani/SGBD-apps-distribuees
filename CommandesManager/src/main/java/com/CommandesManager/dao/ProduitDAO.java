package com.CommandesManager.dao;

import com.CommandesManager.entities.Produit;

import javax.persistence.EntityManager;
import java.util.List;

public class ProduitDAO {

    private static ProduitDAO instance;
    private EntityManager em = Utilitaire.getEntityManager();

    private ProduitDAO() {}

    public static ProduitDAO getInstance() {
        if (instance == null) {
            instance = new ProduitDAO();
        }
        return instance;
    }

    public void ajouter(Produit p) {
        em.persist(p);
    }

    public Produit trouver(Long id) {
        return em.find(Produit.class, id);
    }

    public List<Produit> afficherTous() {
        return em.createQuery("FROM Produit", Produit.class).getResultList();
    }

    public void modifier(Produit p) {
        em.merge(p);
    }

    public void supprimer(Produit p) {
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    public List<Object[]> produitsPlusVendus() {
        return em.createQuery(
                "SELECT p.nom, SUM(l.quantite) " +
                        "FROM LigneCommande l JOIN l.produit p " +
                        "GROUP BY p.nom ORDER BY SUM(l.quantite) DESC"
        ).getResultList();
    }
}