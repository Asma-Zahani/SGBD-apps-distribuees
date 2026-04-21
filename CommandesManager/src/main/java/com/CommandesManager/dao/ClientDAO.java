package com.CommandesManager.dao;

import com.CommandesManager.entities.Client;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientDAO {

    private static ClientDAO instance;
    private EntityManager em = Utilitaire.getEntityManager();

    private ClientDAO() {}

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new ClientDAO();
        }
        return instance;
    }

    public void ajouter(Client c) {
        em.persist(c);
    }

    public Client trouver(Long id) {
        return em.find(Client.class, id);
    }

    public List<Client> afficherTous() {
        return em.createQuery("FROM Client", Client.class).getResultList();
    }

    public void modifier(Client c) {
        em.merge(c);
    }

    public void supprimer(Client c) {
        em.remove(em.contains(c) ? c : em.merge(c));
    }
}