package EtudiantJPA.dao;

import EtudiantJPA.model.Option;
import jakarta.persistence.*;

public class OptionDao {
    EntityManager em = Utilitaire.getEntityManager();
    private static OptionDao instance;

    private OptionDao(){}

    public static OptionDao getInstance(){
        if(instance == null)
            instance = new OptionDao();
        return instance;
    }

    public void ajouter(Option o){
        em.persist(o);
    }

    public Option rechercher(int id){
        return em.find(Option.class, id);
    }
}
