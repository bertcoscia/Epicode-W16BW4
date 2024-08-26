package aalbertocoscia.dao;

import aalbertocoscia.entities.Autobus;
import aalbertocoscia.entities.Mezzo;
import aalbertocoscia.entities.Tram;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

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

    public Mezzo findById(long idMezzo) {
        Mezzo found = em.find(Mezzo.class, idMezzo);
        //if (found == null) throw new NotFoundException(idMezzo);
        return found;
    }

    public Autobus findAutobusById(long catId) {
        Autobus found = em.find(Autobus.class, catId);
        //if (found == null) throw new NotFoundException(AutobusId);
        return found;
    }

    public Tram findTramById(long tramId) {
        Tram found = em.find(Tram.class, tramId);
        //if (found == null) throw new NotFoundException(tramId);
        return found;
    }

    public void findMezzoByIdAndDelete(UUID toDelete) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query deleteQuery = em.createQuery("DELETE FROM Animal a WHERE a.id_mezzo = :toDelete"); // DELETE FROM animals WHERE name = 'Felix'
        deleteQuery.setParameter("id_mezzo", toDelete);
        int numCancellati = deleteQuery.executeUpdate();

        transaction.commit();
        System.out.println("Ho cancellato il mezzo con id: " + toDelete);
    }
}
