package aalbertocoscia.dao;

import aalbertocoscia.entities.Venditore;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class VenditoreDAO {
    private final EntityManager em;

    public VenditoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Venditore v) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(v);
        transaction.commit();
        System.out.println("Venditore " + v.getIdVenditore() + " salvato correttamente");
    }

    public Venditore findVenditoreById(String id) {
        Venditore found = em.find(Venditore.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findVenditoreByIdAndDelete(String id) {
        Venditore found = findVenditoreById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Venditore " + found.getIdVenditore() + " rimosso correttamente");
    }

    public List<Venditore> findAllVenditori() {
        TypedQuery<Venditore> query = em.createQuery(
                "SELECT u FROM Venditore u",
                Venditore.class
        );
        return query.getResultList();
    }
}
