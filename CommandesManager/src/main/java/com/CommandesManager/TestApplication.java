package com.CommandesManager;

import com.CommandesManager.dao.*;
import com.CommandesManager.entities.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;

public class TestApplication {

    public static void main(String[] args) {

        Utilitaire.setPU("commandePU");
        EntityManager em = Utilitaire.getEntityManager();

        em.getTransaction().begin();

        // =====================================================
        // AJOUTER 2 CLIENTS
        // =====================================================
        Client c1 = new Client(null, "Ali", "ali@mail.com");
        Client c2 = new Client(null, "Sami", "sami@mail.com");

        ClientDAO.getInstance().ajouter(c1);
        ClientDAO.getInstance().ajouter(c2);

        System.out.println("✔ 2 clients ajoutés");

        // =====================================================
        // AJOUTER 5 PRODUITS
        // =====================================================
        Produit p1 = new Produit(null, "PC", 2000, 10);
        Produit p2 = new Produit(null, "Souris", 50, 20);
        Produit p3 = new Produit(null, "Clavier", 120, 15);
        Produit p4 = new Produit(null, "Ecran", 500, 8);
        Produit p5 = new Produit(null, "Casque", 150, 12);

        ProduitDAO.getInstance().ajouter(p1);
        ProduitDAO.getInstance().ajouter(p2);
        ProduitDAO.getInstance().ajouter(p3);
        ProduitDAO.getInstance().ajouter(p4);
        ProduitDAO.getInstance().ajouter(p5);

        System.out.println("✔ 5 produits ajoutés");

        // =====================================================
        // CREER UNE COMMANDE AVEC 3 PRODUITS
        // =====================================================

        // récupération (important pour être en état managed)
        Client client = ClientDAO.getInstance().trouver(c1.getId());

        Produit pr1 = ProduitDAO.getInstance().trouver(p1.getId());
        Produit pr2 = ProduitDAO.getInstance().trouver(p2.getId());
        Produit pr3 = ProduitDAO.getInstance().trouver(p3.getId());

        // Vérification stock AVANT commande
        if (pr1.getStock() < 2 || pr2.getStock() < 3 || pr3.getStock() < 1) {
            throw new RuntimeException("Stock insuffisant");
        }

        // lignes de commande
        LigneCommande l1 = new LigneCommande(null, 2, pr1, null);
        LigneCommande l2 = new LigneCommande(null, 3, pr2, null);
        LigneCommande l3 = new LigneCommande(null, 1, pr3, null);

        List<LigneCommande> lignes = Arrays.asList(l1, l2, l3);

        // décrémenter stock
        pr1.setStock(pr1.getStock() - 2);
        pr2.setStock(pr2.getStock() - 3);
        pr3.setStock(pr3.getStock() - 1);

        // création commande
        Commande cmd = new Commande();
        cmd.setClient(client);
        cmd.setDateCommande(LocalDate.now());
        cmd.setLignes(lignes);

        for (LigneCommande l : lignes) {
            l.setCommande(cmd);
        }

        CommandeDAO.getInstance().ajouter(cmd);

        System.out.println("✔ Commande créée");

        // =====================================================
        // 4. VERIFIER STOCK
        // =====================================================
        System.out.println("\nSTOCK APRÈS COMMANDE :");

        System.out.println(pr1.getNom() + " = " + pr1.getStock());
        System.out.println(pr2.getNom() + " = " + pr2.getStock());
        System.out.println(pr3.getNom() + " = " + pr3.getStock());
        System.out.println(p4.getNom() + " = " + p4.getStock());
        System.out.println(p5.getNom() + " = " + p5.getStock());

        // =====================================================
        // 5. PRODUITS LES PLUS VENDUS
        // =====================================================
        System.out.println("\nPRODUITS LES PLUS VENDUS :");

        List<Object[]> stats = ProduitDAO.getInstance().produitsPlusVendus();

        for (Object[] row : stats) {
            System.out.println("Produit: " + row[0] + " | Quantité: " + row[1]);
        }

        em.getTransaction().commit();
        em.close();

        System.out.println("\n✔ TEST COMPLET TERMINÉ");
    }
}