package aalbertocoscia.dao;

import aalbertocoscia.entities.Biglietto;
import aalbertocoscia.entities.Venditore;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BigliettoDAO {
    private final EntityManager em;

    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Biglietto biglietto) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Biglietto " + biglietto.getId() + " salvato correttamente");
    }

    public Biglietto findBigliettoById(String id) {
        Biglietto found = em.find(Biglietto.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void deleteById(String id) {
        Biglietto found = findBigliettoById(id);
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

    public List<Biglietto> findBigliettiNonTimbrati() {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietto b WHERE b.timbrato = false",
                Biglietto.class
        );
        return query.getResultList();
    }


    public List<Biglietto> findBigliettiByDataVidimazione(LocalDate dataVidimazione) {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietto b WHERE b.dataVidimazione = :dataVidimazione",
                Biglietto.class
        );
        query.setParameter("dataVidimazione", dataVidimazione);
        return query.getResultList();
    }

    public int countBigliettiByViaggio(String idViaggio) {
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.viaggio.idViaggio = :idViaggio"
        );
        query.setParameter("idViaggio", UUID.fromString(idViaggio));
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List<Biglietto> findBigliettiByViaggio(String id) {
        TypedQuery<Biglietto> query = em.createQuery(
                "SELECT b FROM Biglietto b WHERE b.viaggio.idViaggio = :id",
                Biglietto.class
        );
        query.setParameter("id", UUID.fromString(id));
        return query.getResultList();
    }

    public int countBigliettiEmessiInPeriodoDiTempo(String startDate, String endDate) {
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.data_emissione BETWEEN :startDate AND :endDate"
        );
        query.setParameter("startDate", LocalDate.parse(startDate));
        query.setParameter("endDate", LocalDate.parse(endDate));
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countBigliettiEmessiInPeriodoDiTempoByVenditore(String startDate, String endDate, String idVenditore) {
        Venditore found = em.find(Venditore.class, UUID.fromString(idVenditore));
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.data_emissione BETWEEN :startDate AND :endDate AND b.venditore.idVenditore = :idVenditore"
        );
        query.setParameter("startDate", LocalDate.parse(startDate));
        query.setParameter("endDate", LocalDate.parse(endDate));
        query.setParameter("idVenditore", found.getIdVenditore());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countBigliettiTimbratiInPeriodoDiTempo(String startDate, String endDate) {
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.dataVidimazione BETWEEN :startDate AND :endDate"
        );
        query.setParameter("startDate", LocalDate.parse(startDate));
        query.setParameter("endDate", LocalDate.parse(endDate));
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public int countBigliettiTimbratiByMezzo(String idMezzo) {
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b JOIN Viaggio v ON b.viaggio.idViaggio = v.idViaggio WHERE v.mezzo.idMezzo = :idMezzo"
        );
        query.setParameter("idMezzo", UUID.fromString(idMezzo));
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public Long countBigliettiByTessera(String idTessera) {
        Query query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.tessera.idTessera = :idTessera"
        );
        query.setParameter("idTessera", UUID.fromString(idTessera));
        return (Long) query.getSingleResult();
    }

}
