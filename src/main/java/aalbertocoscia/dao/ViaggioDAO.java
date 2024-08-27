package aalbertocoscia.dao;

import aalbertocoscia.entities.Viaggio;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class ViaggioDAO {
    private final EntityManager em;

    public ViaggioDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Viaggio v) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(v);
        transaction.commit();
        System.out.println("Viaggio " + v.getIdViaggio() + " correttamente salvato");
    }

    public Viaggio findViaggioById(String id) {
        Viaggio found = em.find(Viaggio.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findViaggioByIdAndDelete(String id) {
        Viaggio found = findViaggioById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Viaggio numero " + found.getIdViaggio() + " rimossa correttamente");
    }

    public List<Viaggio> findAllViaggi() {
        TypedQuery<Viaggio> query = em.createQuery(
                "SELECT v FROM Viaggio v",
                Viaggio.class
        );
        return query.getResultList();
    }
}
