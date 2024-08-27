package aalbertocoscia;

import aalbertocoscia.dao.ManutenzioneDAO;
import aalbertocoscia.dao.MezzoDAO;
import aalbertocoscia.dao.TesseraDAO;
import aalbertocoscia.dao.TrattaDAO;
import aalbertocoscia.dao.UserDAO;
import aalbertocoscia.dao.VenditoreDAO;
import aalbertocoscia.entities.DistributoreAutomatico;
import aalbertocoscia.entities.Rivenditore;
import aalbertocoscia.entities.Tratta;
import aalbertocoscia.enums.StatoDistributoreAutomatico;
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
        TesseraDAO ted = new TesseraDAO(em);
        VenditoreDAO vd = new VenditoreDAO(em);
        TrattaDAO trd = new TrattaDAO(em);

        //User sergioMattarella = new User("Sergio", "Mattarella", "1938-05-12");
        //User user2 = new User(faker.dune().character(), faker.name().lastName(), "1998-04-15");
        //ud.save(sergioMattarella);
        //ud.save(user2);
        //User sergioMattarellaFromDb = ud.findUserById("e8161585-b715-4be9-b59c-024c6205918b");
        //User user2FromDb = ud.findUserById("d6521de9-054e-457e-9fc4-436346feec48");

        //Tessera tes1 = new Tessera("2024-01-01", sergioMattarellaFromDb);
        //Tessera tes2 = new Tessera("2024-08-21", user2FromDb);

        DistributoreAutomatico dist1 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.ATTIVO);
        DistributoreAutomatico dist2 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.FUORI_SERVIZIO);
        Rivenditore riv1 = new Rivenditore(faker.address().fullAddress(), faker.company().name());

        //vd.save(dist1);
        //vd.save(dist2);
        //vd.save(riv1);

        Tratta tra1 = new Tratta("81", faker.address().fullAddress(), faker.address().fullAddress(), faker.number().numberBetween(20, 90));
        Tratta tra2 = new Tratta("545", faker.address().fullAddress(), faker.address().fullAddress(), faker.number().numberBetween(20, 90));

        System.out.println(trd.findAllTratte());

        em.close();
        emf.close();
    }
}
