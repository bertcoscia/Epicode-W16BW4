package aalbertocoscia.dao;

import aalbertocoscia.entities.Manutenzione;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class ManutenzioneDAO {

    private final EntityManager em;

    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Manutenzione t) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(t);
        transaction.commit();
        System.out.println("Manutenzione numero " + t.getIdManutenzione() + " salvata correttamente");
    }

    public Manutenzione findManutenzioneById(String id) {
        Manutenzione found = em.find(Manutenzione.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findManutenzioneByIdAndDelete(String id) {
        Manutenzione found = findManutenzioneById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Manutenzione numero " + found.getIdManutenzione() + " rimossa correttamente");
    }

    public List<Manutenzione> findAllManutenzioni() {
        TypedQuery<Manutenzione> query = em.createQuery(
                "SELECT t FROM Manutenzioni t",
                Manutenzione.class
        );
        return query.getResultList();
    }
}
