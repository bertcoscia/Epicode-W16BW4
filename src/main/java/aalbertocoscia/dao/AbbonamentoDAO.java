package aalbertocoscia.dao;

import aalbertocoscia.entities.Abbonamento;
import aalbertocoscia.enums.DurataAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO extends TitoloViaggioDAO<Abbonamento> {

    public AbbonamentoDAO(EntityManager em) {
        super(em, Abbonamento.class);
    }

    public void save(Abbonamento abbonamento) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(abbonamento);
        transaction.commit();
        System.out.println("Abbonamento " + abbonamento.getId() + " salvato correttamente");
    }

    public void deleteById(String id) {
        Abbonamento found = findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Biglietto " + found.getId() + " rimosso correttamente");
        } else {
            System.out.println("Biglietto con ID " + id + " non trovato");
        }
    }

    public List<Abbonamento> findAbbonamentiByScadenza(LocalDate scadenza) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamenti a WHERE a.data_scadenza <= :scadenza",
                Abbonamento.class
        );
        query.setParameter("scadenza", scadenza);
        return query.getResultList();
    }

    // Trova tutti gli abbonamenti con una certa durata
    public List<Abbonamento> findAbbonamentiByDurata(DurataAbbonamento durata) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamenti a WHERE a.durata = :durata",
                Abbonamento.class
        );
        query.setParameter("durata", durata);
        return query.getResultList();
    }
}
