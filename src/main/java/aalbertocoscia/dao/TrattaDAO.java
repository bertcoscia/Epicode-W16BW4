package aalbertocoscia.dao;

import aalbertocoscia.entities.Tratta;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TrattaDAO {
    private final EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta t) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(t);
        transaction.commit();
        System.out.println("Tratta " + t.getIdTratta() + " salvata correttamente");
    }

    public Tratta findTrattaById(String id) {
        Tratta found = em.find(Tratta.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findTrattaByIdAndDelete(String id) {
        Tratta found = findTrattaById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Tratta " + found.getIdTratta() + " rimossa correttamente");
    }

    public List<Tratta> findAllTratte() {
        TypedQuery<Tratta> query = em.createQuery(
                "SELECT t FROM Tratta t",
                Tratta.class
        );
        return query.getResultList();
    }
}
