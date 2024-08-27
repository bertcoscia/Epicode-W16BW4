package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.*;
import aalbertocoscia.enums.DurataAbbonamento;
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
        TesseraDAO td = new TesseraDAO(em);
        VenditoreDAO vd = new VenditoreDAO(em);
        AbbonamentiDAO ad = new AbbonamentiDAO(em);
        BigliettiDAO bd = new BigliettiDAO(em);


        User sergioMattarella = new User("Sergio", "Mattarella", "1938-05-12");
        User user2 = new User(faker.dune().character(), faker.name().lastName(), "1998-04-15");
        //ud.save(sergioMattarella);
        //ud.save(user2);
        User sergioMattarellaFromDb = ud.findUserById("388bbf52-783c-4dbf-8c02-955738e21e35");
        User user2FromDb = ud.findUserById("1cef502c-3b41-4d19-bd80-bc2644fe48fe");
        //System.out.println("l'utente trovato è:" + sergioMattarellaFromDb);

        //Tessera tes1 = new Tessera("2024-01-01", sergioMattarellaFromDb);
        //Tessera tes2 = new Tessera("2024-08-21", user2FromDb);

        DistributoreAutomatico dist1 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.ATTIVO);
        DistributoreAutomatico dist2 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.FUORI_SERVIZIO);
        Rivenditore riv1 = new Rivenditore(faker.address().fullAddress(), faker.company().name());

        //vd.save(dist1);
        //vd.save(dist2);
        //vd.save(riv1);

        Abbonamenti abbonamento1 = new Abbonamenti("2024-08-27", DurataAbbonamento.SETTIMANALE);
        Abbonamenti abbonamento2 = new Abbonamenti("2024-08-27", DurataAbbonamento.MENSILE);


        Biglietti biglietto1 = new Biglietti("2024-08-27");

        //ad.save(abbonamento1);
        //ad.save(abbonamento2);
        //bd.save(biglietto1);


        em.close();
        emf.close();
    }
}
