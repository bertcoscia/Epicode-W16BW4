package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.*;
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
        VenditoreDAO ved = new VenditoreDAO(em);
        TrattaDAO trd = new TrattaDAO(em);
        MezzoDAO med = new MezzoDAO(em);
        ManutenzioneDAO mad = new ManutenzioneDAO(em);
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        BigliettoDAO bd = new BigliettoDAO(em);
        ViaggioDAO vid = new ViaggioDAO(em);


        User sergioMattarella = new User("Sergio", "Mattarella", "1938-05-12");
        User user2 = new User(faker.dune().character(), faker.name().lastName(), "1998-04-15");
        //ud.save(sergioMattarella);
        //ud.save(user2);

        //User sergioMattarellaFromDb = ud.findUserById("b6b1e56c-e3e7-45f4-a26a-fef02b2ae4dc");
        //User user2FromDb = ud.findUserById("85afd4c3-832e-4b8e-8fcb-4d9eb9509557");
        //Tessera tes1 = new Tessera("2024-01-01", sergioMattarellaFromDb);
        //Tessera tes2 = new Tessera("2024-08-21", user2FromDb);

        //Tessera tes1FromDb = ted.findTesseraById("0b1f7927-7076-4104-ba79-2bd7074f7c46");

        //ted.save(tes1);
        //ted.save(tes2);

        DistributoreAutomatico dist1 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.ATTIVO);
        DistributoreAutomatico dist2 = new DistributoreAutomatico(faker.address().fullAddress(), StatoDistributoreAutomatico.FUORI_SERVIZIO);
        Rivenditore riv1 = new Rivenditore(faker.address().fullAddress(), faker.company().name());
        //DistributoreAutomatico dist1FromDb = ved.findDistAutById("2bdb834e-c2cf-4d1f-941b-973d8e528613");

        //vd.save(dist1);
        //vd.save(dist2);
        //vd.save(riv1);


        Tratta tra1 = new Tratta("81", faker.address().fullAddress(), faker.address().fullAddress(), faker.number().numberBetween(20, 90));
        Tratta tra2 = new Tratta("545", faker.address().fullAddress(), faker.address().fullAddress(), faker.number().numberBetween(20, 90));

        //trd.save(tra1);
        //trd.save(tra2);

        //Tratta tra1FromDb = trd.findTrattaById("3b0df10a-e518-4a11-b60a-f36732487528");
        //Tratta tra2FromDb = trd.findTrattaById("853ddbe3-a495-45c6-926b-64ea29d94d64");

        Autobus mez1 = new Autobus(30, StatoMezzo.IN_SERVIZIO);
        Autobus mez2 = new Autobus(25, StatoMezzo.IN_MANUTENZIONE);
        Tram tram2 = new Tram(50, StatoMezzo.IN_SERVIZIO);
        //md.save(mez1);
        //md.save(mez2);
        //md.save(tram2);

        //Mezzo mez1FromDb = med.findMezzoById("083c5d43-0f72-47ce-aedd-22f118c28c6d");
        //Mezzo mez2FromDb = med.findMezzoById("71de834d-0dae-4fa1-9431-8c15e2d8a1cd");
        //Mezzo tram2FromDb = med.findMezzoById("b63d1268-77fa-4c02-89ba-e25ae64c154d");
        //Manutenzione man1 = new Manutenzione("2020-07-26", "2020-10-05", "guasto al motore", mez1FromDb);
        //Manutenzione man2 = new Manutenzione("2021-01-06", "2021-01-15", "cambio gomme", mez2FromDb);
        //mad.save(man1);
        //mad.save(man2);


        //Abbonamento abbonamento1 = new Abbonamento("2024-08-27", DurataAbbonamento.SETTIMANALE);
        //Abbonamento abbonamento2 = new Abbonamento("2024-08-27", DurataAbbonamento.MENSILE);
        //Biglietto biglietto1 = new Biglietto("2024-08-27");
        //Biglietto biglietto1FromDb = bd.findById("cb147a2d-67b6-4e36-8f47-e4a792cb9878");

        //ad.save(abbonamento1);
        //ad.save(abbonamento2);
        //bd.save(biglietto1);

        //Viaggio viaggio1 = new Viaggio(54, mez1FromDb, tra1FromDb);
        //Viaggio viaggio2 = new Viaggio(50, mez1FromDb, tra1FromDb);
        //Viaggio viaggio3 = new Viaggio(30, tram2FromDb, tra2FromDb);
        //Viaggio viaggio4 = new Viaggio(40, tram2FromDb, tra2FromDb);
        //Viaggio viaggio1FromDb = vid.findViaggioById("ab0c971d-75d1-4ec0-8cdd-b162226d34bc");

        //vid.save(viaggio1);
        //vid.save(viaggio2);
        //vid.save(viaggio3);
        //vid.save(viaggio4);
//
        //Biglietto biglietto1 = dist1FromDb.emettiBiglietto("2024-08-27");
        //Biglietto biglietto2 = dist1FromDb.emettiBiglietto("2024-05-12");
        //Abbonamento abb1 = dist1FromDb.emettiAbbonamento("2024-08-27", DurataAbbonamento.MENSILE, tes1FromDb);

        //System.out.println(ved.countBigliettiByVenditore("2bdb834e-c2cf-4d1f-941b-973d8e528613"));
        //System.out.println(ved.countAbbonamentiByVenditore("2bdb834e-c2cf-4d1f-941b-973d8e528613"));
        //System.out.println(ved.countAbbonamentiAndBigliettiByVenditore("2bdb834e-c2cf-4d1f-941b-973d8e528613"));
        //System.out.println(vid.countViaggi(mez1FromDb, tra1FromDb));
        //System.out.println(vid.countViaggi(tram2FromDb, tra2FromDb));
        //System.out.println(vid.tempoMedioViaggio(tram2FromDb, tra2FromDb));
        //System.out.println(vid.tempoMedioViaggio(mez1FromDb, tra1FromDb));


        User mattarellaDb = ud.findUserById("b6b1e56c-e3e7-45f4-a26a-fef02b2ae4dc");
        Abbonamento abb1 = ad.findById("4972d730-1104-46e9-97a8-df1213a5bda6");
        Tessera tesseraMattarella = new Tessera("2024-04-01", mattarellaDb, abb1);
        Tessera tesseraMattarellaDB = ted.findTesseraById("b931966e-bb03-43e5-bdfb-9824dae0b9f2");
        //System.out.println(mattarellaDb);

        //System.out.println(mattarellaDb.isAbbonamentoValido());
        //System.out.println(tesseraMattarellaDB.isAbbonamentoValido());

        //System.out.println(bd.countBigliettiByViaggio("ab0c971d-75d1-4ec0-8cdd-b162226d34bc"));

        Biglietto big = bd.findById("4f09668e-f40c-47ce-ab08-40477401e499");
        Viaggio v = vid.findViaggioById("ab0c971d-75d1-4ec0-8cdd-b162226d34bc");
        DistributoreAutomatico dis = ved.findDistAutById("2bdb834e-c2cf-4d1f-941b-973d8e528613");
        Biglietto big2 = new Biglietto("2024-08-28", dis);

        //System.out.println(bd.countBigliettiEmessiInPeriodoDiTempo("2024-08-01", "2024-08-28"));
        //System.out.println(bd.countBigliettiEmessiInPeriodoDiTempoByVenditore("2024-08-01", "2024-08-28", dis.getIdVenditore().toString()));
        System.out.println(bd.countBigliettiTimbratiInPeriodoDiTempo("2024-06-27", "2024-06-28"));

        em.close();
        emf.close();
    }
}
