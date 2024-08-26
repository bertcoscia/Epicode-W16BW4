package aalbertocoscia;

import aalbertocoscia.dao.UserDAO;
import aalbertocoscia.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W16BW4");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        User sergioMattarella = new User("Sergio", "Mattarella", LocalDate.parse("1938-05-12"));

        User sergioMattarellaFromDb = ud.findUserById("fbabb1b7-d953-4a41-813c-cef8e9e38bbc");
        System.out.println(sergioMattarellaFromDb);
    }
}
