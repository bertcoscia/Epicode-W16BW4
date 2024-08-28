package aalbertocoscia.dao;

import aalbertocoscia.entities.Biglietto;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettoDAO extends TitoloViaggioDAO<Biglietto> {

    public BigliettoDAO(EntityManager em) {
        super(em, Biglietto.class);
    }

    public void save(Biglietto biglietto) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Biglietto " + biglietto.getId() + " salvato correttamente");
    }

    public Biglietto findById(String id) {
        Biglietto found = em.find(Biglietto.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void deleteById(String id) {
        Biglietto found = findById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Biglietto " + found.getId() + " rimosso correttamente");
        } else {
            System.out.println("Biglietto con ID " + id + " non trovato");
        }
    }

    public List<Biglietto> findBigliettiAttivi() {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietti b WHERE b.attivo = true",
                Biglietto.class
        );
        return query.getResultList();
    }


    public List<Biglietto> findBigliettiByDataVidimazione(LocalDate dataVidimazione) {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietti b WHERE b.dataVidimazione <= :dataVidimazione",
                Biglietto.class
        );
        query.setParameter("dataVidimazione", dataVidimazione);
        return query.getResultList();
    }
}
