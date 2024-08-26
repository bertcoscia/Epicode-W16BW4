package aalbertocoscia;

import aalbertocoscia.dao.TesseraDAO;
import aalbertocoscia.dao.UserDAO;
import aalbertocoscia.entities.Tessera;
import aalbertocoscia.entities.User;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W16BW4");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        TesseraDAO td = new TesseraDAO(em);

        User sergioMattarella = new User("Sergio", "Mattarella", "1938-05-12");
        User user2 = new User(faker.dune().character(), faker.name().lastName(), "1998-04-15");
        User sergioMattarellaFromDb = ud.findUserById("fbabb1b7-d953-4a41-813c-cef8e9e38bbc");
        User user2FromDb = ud.findUserById("b77b1aaf-ee61-4be4-aa12-691e86be8682");

        Tessera tes1 = new Tessera("2024-01-01", sergioMattarellaFromDb);
        Tessera tes2 = new Tessera("2024-08-21", user2FromDb);

        System.out.println(td.findAllTessere());

        em.close();
        emf.close();
    }
}
