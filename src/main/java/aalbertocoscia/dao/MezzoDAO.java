package aalbertocoscia.dao;

import aalbertocoscia.entities.Autobus;
import aalbertocoscia.entities.Mezzo;
import aalbertocoscia.entities.Tram;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
