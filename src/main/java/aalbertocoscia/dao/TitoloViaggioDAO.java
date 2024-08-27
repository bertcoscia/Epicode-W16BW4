package aalbertocoscia.dao;

import aalbertocoscia.entities.TitoloViaggio;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TitoloViaggioDAO<T extends TitoloViaggio> {
    protected final EntityManager em;
    private final Class<T> entityClass;

    public TitoloViaggioDAO(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public void save(T titoloViaggio) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(titoloViaggio);
        transaction.commit();
        System.out.println("Il viaggio" + titoloViaggio.getId() + "è stato salvato correttamente");
    }

    public T findById(UUID id) {
        T found = em.find(entityClass, id);
        if (found == null) throw new NotFoundException(id.toString());
        return found;
    }

    public void deleteById(UUID id) {
        T found = findById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Il viaggio " + id + " è stato rimosso correttamente");
    }

    public List<T> findAll() {
        TypedQuery<T> query = em.createQuery(
                "SELECT t FROM " + entityClass.getSimpleName() + " t",
                entityClass
        );
        return query.getResultList();
    }
}
