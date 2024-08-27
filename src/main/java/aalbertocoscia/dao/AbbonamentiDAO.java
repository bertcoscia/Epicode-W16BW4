package aalbertocoscia.dao;

import aalbertocoscia.entities.Abbonamenti;
import aalbertocoscia.enums.DurataAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AbbonamentiDAO extends TitoloViaggioDAO<Abbonamenti> {

    public AbbonamentiDAO(EntityManager em) {
        super(em, Abbonamenti.class);
    }

    public void save(Abbonamenti abbonamenti) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(abbonamenti);
        transaction.commit();
        System.out.println("Abbonamento " + abbonamenti.getId() + " salvato correttamente");
    }

    public void deleteById(UUID id) {
        Abbonamenti found = findById(id);
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

    public List<Abbonamenti> findAbbonamentiByScadenza(LocalDate scadenza) {
        TypedQuery<Abbonamenti> query = em.createQuery(
                "SELECT a FROM Abbonamenti a WHERE a.data_scadenza <= :scadenza",
                Abbonamenti.class
        );
        query.setParameter("scadenza", scadenza);
        return query.getResultList();
    }

    // Trova tutti gli abbonamenti con una certa durata
    public List<Abbonamenti> findAbbonamentiByDurata(DurataAbbonamento durata) {
        TypedQuery<Abbonamenti> query = em.createQuery(
                "SELECT a FROM Abbonamenti a WHERE a.durata = :durata",
                Abbonamenti.class
        );
        query.setParameter("durata", durata);
        return query.getResultList();
    }
}
