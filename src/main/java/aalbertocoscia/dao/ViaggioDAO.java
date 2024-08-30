package aalbertocoscia.dao;

import aalbertocoscia.entities.Mezzo;
import aalbertocoscia.entities.Tratta;
import aalbertocoscia.entities.Viaggio;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
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

    public int countViaggi(Mezzo mezzo, Tratta tratta) {
        Query query = em.createQuery(
                "SELECT COUNT(v) FROM Viaggio v WHERE v.mezzo = :mezzo AND v.tratta = :tratta"
        );
        query.setParameter("mezzo", mezzo);
        query.setParameter("tratta", tratta);

        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public double tempoMedioViaggio(Mezzo mezzo, Tratta tratta) {
        TypedQuery<Double> query = em.createQuery(
                "SELECT AVG(v.durataEffettiva) FROM Viaggio v WHERE v.mezzo = :mezzo AND v.tratta = :tratta",
                Double.class
        );
        query.setParameter("mezzo", mezzo);
        query.setParameter("tratta", tratta);

        Double tempoMedio = query.getSingleResult();
        return (tempoMedio != null) ? tempoMedio : 0.0;
    }

    public double avgPercorrenzaEffettivaByTratta(String idTratta) {
        Query query = em.createQuery(
                "SELECT AVG(v.durataEffettiva) FROM Viaggio v WHERE v.tratta.idTratta = :idTratta"
        );
        query.setParameter("idTratta", UUID.fromString(idTratta));
        Double avg = (Double) query.getSingleResult();
        return (avg != null) ? avg : 0.0;
    }

    public List<Viaggio> findViaggiByTratta(String idTratta) {
        TypedQuery<Viaggio> query = em.createQuery(
                "SELECT v FROM Viaggio v WHERE v.tratta.idTratta = :idTratta",
                Viaggio.class
        );
        query.setParameter("idTratta", UUID.fromString(idTratta));
        return query.getResultList();
    }

}

