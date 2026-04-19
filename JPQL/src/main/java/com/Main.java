package com;

import com.util.Utilitaire;

public class Main {
    public static void main(String[] args) {
        // Initialiser JPA
        Utilitaire.setPU("jpql_gestion_etudiant");

        // Créer des départements et étudiants de test
        //JPQLQueries.ajouterDonneesTest();

        // Tester les requêtes JPQL
        System.out.println("\n--- Étudiants avec leur département ---");
        JPQLQueries.etudiantsAvecDepartement();

        System.out.println("\n--- Tous les départements ---");
        JPQLQueries.tousDepartements();

        System.out.println("\n--- Étudiants du département Informatique ---");
        JPQLQueries.etudiantsInfo();

        System.out.println("\n--- Nombre d’étudiants par département ---");
        JPQLQueries.countParDept();

        System.out.println("\n--- Départements ayant plus de 1 étudiant ---");
        JPQLQueries.deptPlus1();

        System.out.println("\n--- Étudiants avec moyenne > moyenne globale ---");
        JPQLQueries.moyenneSuperieure();

        System.out.println("\n--- Moyenne des étudiants par département ---");
        JPQLQueries.moyenneParDept();

        System.out.println("\n--- Augmenter la moyenne des étudiants d'Informatique ---");
        JPQLQueries.augmenterMoyenne();

        System.out.println("\n--- Étudiants triés par moyenne décroissante ---");
        JPQLQueries.trierParMoyenne();

        System.out.println("\n--- Département avec la moyenne la plus élevée ---");
        JPQLQueries.deptMoyenneMax();

        System.out.println("\n--- Meilleur étudiant par département ---");
        JPQLQueries.meilleurParDept();

        System.out.println("\n--- Départements sans étudiants ---");
        JPQLQueries.deptSansEtudiants();

        System.out.println("\n--- Supprimer les départements sans étudiants ---");
        JPQLQueries.supprimerDeptVide();
    }
}