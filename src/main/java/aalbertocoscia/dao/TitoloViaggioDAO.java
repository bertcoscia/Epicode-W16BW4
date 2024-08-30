package aalbertocoscia.dao;

import aalbertocoscia.entities.TitoloViaggio;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TitoloViaggioDAO {
    protected final EntityManager em;

    public TitoloViaggioDAO(EntityManager em) {
        this.em = em;
    }

    public void save(TitoloViaggio titoloViaggio) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(titoloViaggio);
        transaction.commit();
        System.out.println("Il viaggio" + titoloViaggio.getId() + "è stato salvato correttamente");
    }

    public TitoloViaggio findById(String id) {
        TitoloViaggio found = em.find(TitoloViaggio.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void deleteById(String id) {
        TitoloViaggio found = findById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Il viaggio " + id + " è stato rimosso correttamente");
    }

    public List<TitoloViaggio> findAll() {
        TypedQuery<TitoloViaggio> query = em.createQuery(
                "SELECT t FROM TitoloViaggio t",
                TitoloViaggio.class
        );
        return query.getResultList();
    }

    public Long countAllTitoliViaggio() {
        Query query = em.createQuery(
                "SELECT COUNT(t) FROM TitoloViaggio t"
        );
        return (Long) query.getSingleResult();
    }
}
