package com;

import com.entities.Departement;
import com.entities.Etudiant;
import com.util.Utilitaire;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JPQLQueries {

    static EntityManager em = Utilitaire.getEntityManager();

    public static void ajouterDonneesTest() {
        em.getTransaction().begin();

        Departement d1 = new Departement(null, "Informatique");
        Departement d2 = new Departement(null, "Math");

        em.persist(d1);
        em.persist(d2);

        Etudiant e1 = new Etudiant(null, "Ali", 15, d1);
        Etudiant e2 = new Etudiant(null, "Sami", 12, d1);
        Etudiant e3 = new Etudiant(null, "Amal", 17, d2);

        em.persist(e1);
        em.persist(e2);
        em.persist(e3);

        em.getTransaction().commit();
        System.out.println("Données de test ajoutées !");
    }

    // Étudiants avec leur département
    public static void etudiantsAvecDepartement(){
        List<Object[]> res = em.createQuery("SELECT e, d FROM Etudiant e JOIN e.departement d").getResultList();

        for(Object[] row : res){
            System.out.println(((Etudiant) row[0]).getNom() + " -> " + ((Departement) row[1]).getNom());
        }
    }

    // Tous les départements même sans étudiants
    public static void tousDepartements(){
        List<Departement> list = em.createQuery("SELECT d FROM Departement d LEFT JOIN d.etudiants").getResultList();

        for (Departement d : list) {
            System.out.print(d.getNom() + " : ");
            if(d.getEtudiants().isEmpty()){
                System.out.println("Aucun étudiant");
            } else {
                for(Etudiant e : d.getEtudiants()){
                    System.out.print(e.getNom() + ", ");
                }
                System.out.println();
            }
        }
    }

    // Étudiants Informatique
    public static void etudiantsInfo(){
        List<Etudiant> list = em.createQuery("SELECT e FROM Etudiant e WHERE e.departement.nom='Informatique'").getResultList();

        list.forEach(e -> System.out.print(e.getNom() + ", "));
        System.out.println();
    }

    // Nombre d’étudiants par département
    public static void countParDept(){
        List<Object[]> res = em.createQuery("SELECT d.nom, COUNT(e) FROM Departement d LEFT JOIN d.etudiants e GROUP BY d.nom").getResultList();

        res.forEach(r -> System.out.println("Département " + r[0] + " : " + r[1] + "étudiant(s)"));
    }

    // Départements > 1 étudiant
    public static void deptPlus1(){
        List<String> res = em.createQuery("SELECT d.nom FROM Departement d JOIN d.etudiants e GROUP BY d.nom HAVING COUNT(e)>1").getResultList();

        res.forEach(System.out::println);
    }

    // Moyenne > moyenne globale
    public static void moyenneSuperieure(){
        List<Etudiant> list = em.createQuery("SELECT e FROM Etudiant e WHERE e.moyenne > (SELECT AVG(e2.moyenne) FROM Etudiant e2)").getResultList();

        list.forEach(e -> System.out.print(e.getNom() + ", "));
        System.out.println();
    }

    // Moyenne par département
    public static void moyenneParDept(){
        List<Object[]> res = em.createQuery("SELECT d.nom, AVG(e.moyenne) FROM Departement d JOIN d.etudiants e GROUP BY d.nom").getResultList();

        res.forEach(r -> System.out.println(r[0] + " : " + r[1]));
    }

    // Update moyenne
    public static void augmenterMoyenne(){
        em.getTransaction().begin();
        Query q = em.createQuery("UPDATE Etudiant e SET e.moyenne = e.moyenne + 1 WHERE e.departement.id IN (SELECT d.id FROM Departement d WHERE d.nom = 'Informatique')");
        int rows = q.executeUpdate();
        em.getTransaction().commit();

        System.out.println("Nombre d'étudiants modifiés : " + rows);
    }

    // Trier par moyenne
    public static void trierParMoyenne(){
        List<Etudiant> list = em.createQuery("SELECT e FROM Etudiant e JOIN e.departement d ORDER BY e.moyenne DESC").getResultList();

        list.forEach(e -> System.out.println(e.getNom() + " (" + e.getMoyenne() + ") -> " + e.getDepartement().getNom()));
    }

    // Département avec la moyenne la plus élevée
    public static void deptMoyenneMax(){
        List<Object[]> res = em.createQuery("SELECT d.nom, AVG(e.moyenne) as m FROM Departement d JOIN d.etudiants e GROUP BY d.nom ORDER BY m DESC").setMaxResults(1).getResultList();

        res.forEach(r -> System.out.println(r[0] + " : " + r[1]));
    }

    // Meilleur étudiant par département
    public static void meilleurParDept(){
        List<Etudiant> list = em.createQuery("SELECT e FROM Etudiant e WHERE e.moyenne = (SELECT MAX(e2.moyenne) FROM Etudiant e2 WHERE e2.departement = e.departement)").getResultList();

        list.forEach(e -> System.out.println(e.getNom() + " (" + e.getMoyenne() + ") -> " + e.getDepartement().getNom()));
    }

    // Départements sans étudiants
    public static void deptSansEtudiants(){
        List<Departement> list = em.createQuery("SELECT d FROM Departement d WHERE d.etudiants IS EMPTY").getResultList();

        if(list.isEmpty()){
            System.out.println("Aucun département vide");
        } else {
            list.forEach(d -> System.out.println(d.getNom()));
        }
    }

    // Supprimer départements sans étudiants
    public static void supprimerDeptVide(){
        em.getTransaction().begin();
        Query q = em.createQuery("DELETE FROM Departement d WHERE d.etudiants IS EMPTY");
        int rows = q.executeUpdate();
        em.getTransaction().commit();

        System.out.println("Départements supprimés : " + rows);
    }

}