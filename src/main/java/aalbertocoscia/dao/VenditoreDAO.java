package aalbertocoscia.dao;

import aalbertocoscia.entities.DistributoreAutomatico;
import aalbertocoscia.entities.Rivenditore;
import aalbertocoscia.entities.Venditore;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public DistributoreAutomatico findDistAutById(String id) {
        DistributoreAutomatico found = em.find(DistributoreAutomatico.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public Rivenditore findRivenditoreById(String id) {
        Rivenditore found = em.find(Rivenditore.class, UUID.fromString(id));
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

    public int countBigliettiByVenditore(String id) {
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.venditore.idVenditore = :id"
        );
        query.setParameter("id", UUID.fromString(id));
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countAbbonamentiByVenditore(String id) {
        Query query = em.createQuery(
                "SELECT COUNT(a) FROM Abbonamento a WHERE a.venditore.idVenditore = :id"
        );
        query.setParameter("id", UUID.fromString(id));
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public Map<String, Integer> countAbbonamentiAndBigliettiByVenditore(String id) {
        int numAbbonamenti = countAbbonamentiByVenditore(id);
        int numBiglietti = countBigliettiByVenditore(id);
        Map<String, Integer> result = new HashMap<>();
        result.put("Abbonamenti", numAbbonamenti);
        result.put("Biglietti", numBiglietti);
        return result;
    }
}
