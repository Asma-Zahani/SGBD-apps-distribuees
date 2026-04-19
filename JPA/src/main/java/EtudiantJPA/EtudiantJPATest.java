package EtudiantJPA;

import EtudiantJPA.dao.*;
import EtudiantJPA.model.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EtudiantJPATest {

    public static void main(String[] args) {

        Utilitaire.setPU("EtudiantPU");

        EntityManager em = Utilitaire.getEntityManager();

        em.getTransaction().begin();

        // Ajouter options si elles n'existent pas
        Option op1 = OptionDao.getInstance().rechercher(1);
        Option op2 = OptionDao.getInstance().rechercher(2);

        if(op1 == null) {
            op1 = new Option(1, "DSI");
            OptionDao.getInstance().ajouter(op1);
        }

        if(op2 == null) {
            op2 = new Option(2, "MDW");
            OptionDao.getInstance().ajouter(op2);
        }

        // Ajouter étudiant seulement s'il n'existe pas
        Etudiant e = EtudiantDao.getInstance().rechercherById("Mat1");

        if(e == null){
            Etudiant e1 = new Etudiant("Mat1", "Saleh", 24, op1);
            EtudiantDao.getInstance().ajouter(e1);
        }

        em.getTransaction().commit();

        // =========================
        // TEST DES MÉTHODES
        // =========================

        // Rechercher par ID
        Etudiant found = EtudiantDao.getInstance().rechercherById("Mat1");
        System.out.println("Trouvé: " + found.getNom());

        // Rechercher par nom
        Etudiant byName = EtudiantDao.getInstance().rechercherByNom("Saleh");
        System.out.println("Par nom: " + byName.getMatricule());

        // Lister tous
        List<Etudiant> list = EtudiantDao.getInstance().listerAll();
        System.out.println("Liste:");
        for(Etudiant et : list){
            System.out.println(et.getMatricule() + " - " + et.getNom());
        }

        // Modifier
        em.getTransaction().begin();
        found.setNom("Saleh_Modifié");
        EtudiantDao.getInstance().modifier(found);
        em.getTransaction().commit();

        System.out.println("Après modification: " + EtudiantDao.getInstance().rechercherById("Mat1").getNom());

        // Supprimer
        em.getTransaction().begin();
        EtudiantDao.getInstance().supprimer(found);
        em.getTransaction().commit();
        System.out.println("Supprimé");

        System.out.println("TEST COMPLET OK");
    }
}