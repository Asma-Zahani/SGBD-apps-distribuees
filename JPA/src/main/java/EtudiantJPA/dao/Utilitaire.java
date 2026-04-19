package EtudiantJPA.dao;

import jakarta.persistence.*;

public class Utilitaire {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void setPU(String pu) {
        emf = Persistence.createEntityManagerFactory(pu);
        em = emf.createEntityManager();
    }
}
