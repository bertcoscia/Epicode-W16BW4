package aalbertocoscia.dao;

import aalbertocoscia.entities.Tessera;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TesseraDAO {
    private final EntityManager em;

    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera t) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(t);
        transaction.commit();
        System.out.println("Tessera numero " + t.getIdTessera() + " salvata correttamente");
    }

    public Tessera findTesseraById(String id) {
        Tessera found = em.find(Tessera.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findTesseraByIdAndDelete(String id) {
        Tessera found = findTesseraById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Tessera numero " + found.getIdTessera() + " rimossa correttamente");
    }

    public List<Tessera> findAllTessere() {
        TypedQuery<Tessera> query = em.createQuery(
                "SELECT t FROM Tessera t",
                Tessera.class
        );
        return query.getResultList();
    }
}
