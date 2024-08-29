package aalbertocoscia.dao;

import aalbertocoscia.entities.Autobus;
import aalbertocoscia.entities.Mezzo;
import aalbertocoscia.entities.Tram;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();
        System.out.println("Il mezzo con id " + mezzo.getIdMezzo() + " è stato salvato correttamente!");
    }

    public Mezzo findMezzoById(String idMezzo) {
        Mezzo found = em.find(Mezzo.class, UUID.fromString(idMezzo));
        if (found == null) throw new NotFoundException(idMezzo);
        return found;
    }

    public Autobus findAutobusById(String AutobusId) {
        Autobus found = em.find(Autobus.class, AutobusId);
        if (found == null) throw new NotFoundException(AutobusId);
        return found;
    }

    public Tram findTramById(String tramId) {
        Tram found = em.find(Tram.class, tramId);
        if (found == null) throw new NotFoundException(tramId);
        return found;
    }

    public void findMezzoByIdAndDelete(String id) {
        Mezzo found = findMezzoById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Mezzo numero " + found.getIdMezzo() + " rimosso correttamente");
    }

    public List<Mezzo> getAllMezziInServizio() {
        TypedQuery<Mezzo> query = em.createQuery(
                "SELECT m FROM Mezzo m WHERE m.statoMezzo = StatoMezzo.IN_SERVIZIO",
                Mezzo.class
        );
        return query.getResultList();
    }

    public List<Mezzo> getAllMezziInManutenzione() {
        TypedQuery<Mezzo> query = em.createQuery(
                "SELECT me FROM Mezzo me JOIN Manutenzione ma ON me.idMezzo = ma.mezzo.idMezzo WHERE me.statoMezzo = StatoMezzo.IN_MANUTENZIONE",
                Mezzo.class
        );
        return query.getResultList();
    }

    public List<Mezzo> getAllMezzi() {
        TypedQuery<Mezzo> query = em.createQuery(
                "SELECT m FROM Mezzo m",
                Mezzo.class
        );
        return query.getResultList();
    }
}
