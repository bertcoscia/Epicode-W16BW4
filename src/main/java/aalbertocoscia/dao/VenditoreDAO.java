package aalbertocoscia.dao;

import aalbertocoscia.entities.Venditore;
import jakarta.persistence.EntityManager;

public class VenditoreDAO {
    private final EntityManager em;

    public VenditoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Venditore v) {
        
    }
}
