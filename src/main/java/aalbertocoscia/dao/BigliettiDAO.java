package aalbertocoscia.dao;

import aalbertocoscia.entities.Biglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettiDAO extends TitoloViaggioDAO<Biglietto> {

    public BigliettiDAO(EntityManager em) {
        super(em, Biglietto.class);
    }

    public void save(Biglietto biglietto) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Biglietto " + biglietto.getId() + " salvato correttamente");
    }

    public void deleteById(UUID id) {
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
