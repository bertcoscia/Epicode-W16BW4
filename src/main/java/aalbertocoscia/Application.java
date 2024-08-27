package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.*;
import aalbertocoscia.enums.DurataAbbonamento;
import aalbertocoscia.enums.StatoDistributoreAutomatico;
import aalbertocoscia.enums.StatoMezzo;
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
        MezzoDAO md = new MezzoDAO(em);
        ManutenzioneDAO mad = new ManutenzioneDAO(em);
        AbbonamentiDAO ad = new AbbonamentiDAO(em);
        BigliettiDAO bd = new BigliettiDAO(em);


        User sergioMattarella = new User("Sergio", "Mattarella", "1938-05-12");
        User user2 = new User(faker.dune().character(), faker.name().lastName(), "1998-04-15");
        //ud.save(sergioMattarella);
        //ud.save(user2);

        User sergioMattarellaFromDb = ud.findUserById("b6b1e56c-e3e7-45f4-a26a-fef02b2ae4dc");
        User user2FromDb = ud.findUserById("85afd4c3-832e-4b8e-8fcb-4d9eb9509557");
        Tessera tes1 = new Tessera("2024-01-01", sergioMattarellaFromDb);
        Tessera tes2 = new Tessera("2024-08-21", user2FromDb);

        //ted.save(tes1);
        //ted.save(tes2);

        DistributoreAutomatico dist1 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.ATTIVO);
        DistributoreAutomatico dist2 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.FUORI_SERVIZIO);
        Rivenditore riv1 = new Rivenditore(faker.address().fullAddress(), faker.company().name());

        //vd.save(dist1);
        //vd.save(dist2);
        //vd.save(riv1);


        Tratta tra1 = new Tratta("81", faker.address().fullAddress(), faker.address().fullAddress(), faker.number().numberBetween(20, 90));
        Tratta tra2 = new Tratta("545", faker.address().fullAddress(), faker.address().fullAddress(), faker.number().numberBetween(20, 90));

        //trd.save(tra1);
        //trd.save(tra2);

        Autobus mez1 = new Autobus(30, StatoMezzo.IN_SERVIZIO);
        Autobus mez2 = new Autobus(25, StatoMezzo.IN_MANUTENZIONE);
        Tram tram2 = new Tram(50, StatoMezzo.IN_SERVIZIO);
        //md.save(mez1);
        //md.save(mez2);
        //md.save(tram2);

        Mezzo mez1FromDb = md.findMezzoById("6451bf53-c52c-455e-ab22-48bb75f1bc6a");
        Mezzo mez2FromDb = md.findMezzoById("6e0bff32-c84a-4a3d-b1aa-7f350281e817");
        Manutenzione man1 = new Manutenzione("2020-07-26", "2020-10-05", "guasto al motore", mez1FromDb);
        Manutenzione man2 = new Manutenzione("2021-01-06", "2021-01-15", "cambio gomme", mez2FromDb);
        //mad.save(man1);
        //mad.save(man2);


        Abbonamento abbonamento1 = new Abbonamento("2024-08-27", DurataAbbonamento.SETTIMANALE);
        Abbonamento abbonamento2 = new Abbonamento("2024-08-27", DurataAbbonamento.MENSILE);
        Biglietto biglietto1 = new Biglietto("2024-08-27");

        //ad.save(abbonamento1);
        //ad.save(abbonamento2);
        //bd.save(biglietto1);


        em.close();
        emf.close();
    }
}
