package aalbertocoscia.dao;

import aalbertocoscia.entities.Biglietti;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettiDAO extends TitoloViaggioDAO<Biglietti> {

    public BigliettiDAO(EntityManager em) {
        super(em, Biglietti.class);
    }

    public void save(Biglietti biglietti) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietti);
        transaction.commit();
        System.out.println("Biglietto " + biglietti.getId() + " salvato correttamente");
    }

    public void deleteById(UUID id) {
        Biglietti found = findById(id);
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

    public List<Biglietti> findBigliettiAttivi() {
        TypedQuery<Biglietti> query = em.createQuery(
                "SELECT b FROM Biglietti b WHERE b.attivo = true",
                Biglietti.class
        );
        return query.getResultList();
    }


    public List<Biglietti> findBigliettiByDataVidimazione(LocalDate dataVidimazione) {
        TypedQuery<Biglietti> query = em.createQuery(
                "SELECT b FROM Biglietti b WHERE b.dataVidimazione <= :dataVidimazione",
                Biglietti.class
        );
        query.setParameter("dataVidimazione", dataVidimazione);
        return query.getResultList();
    }
}
