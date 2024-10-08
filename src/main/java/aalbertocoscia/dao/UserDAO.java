package aalbertocoscia.dao;

import aalbertocoscia.entities.User;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;
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
    }

    public User findUserById(String id) {
        User found = em.find(User.class, UUID.fromString(id));
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findUserByIdAndDelete(String id) {
        User found = findUserById(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("User " + found.getIdUser() + " rimosso correttamente");
    }

    public List<User> findAllUsers() {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u",
                User.class
        );
        return query.getResultList();
    }

    public User findUserByEmailAndPassword(String email, String password) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email AND u.password = :password",
                    User.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*public boolean isAbbonamentoValidoByUser(User u) {
        User found = findUserById(u.getIdUser().toString());
        LocalDate scadenzaAbbonamento = found.getTessera().getAbbonamento().getData_scadenza();
        return scadenzaAbbonamento.isAfter(LocalDate.now());
    }

    public boolean isAbbonamentoValidoById(String userId) {
        User found = findUserById(userId);
        LocalDate scadenzaAbbonamento = found.getTessera().getAbbonamento().getData_scadenza();
        return scadenzaAbbonamento.isAfter(LocalDate.now());
    }*/
}
