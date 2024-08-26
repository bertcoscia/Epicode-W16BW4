package aalbertocoscia.dao;

import aalbertocoscia.entities.User;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User u) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(u);
        transaction.commit();
        System.out.println("User " + u.getIdUser() + " salvato correttamente");
        em.close();
    }

    public User findUserById(String id) {
        User found = em.find(User.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }
}
