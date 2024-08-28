package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.Biglietto;
import aalbertocoscia.entities.User;
import aalbertocoscia.entities.Venditore;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W16BW4");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();

        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        BigliettoDAO bd = new BigliettoDAO(em);
        ManutenzioneDAO mad = new ManutenzioneDAO(em);
        MezzoDAO med = new MezzoDAO(em);
        TesseraDAO ted = new TesseraDAO(em);
        TitoloViaggioDAO tvd = new TitoloViaggioDAO(em);
        TrattaDAO trd = new TrattaDAO(em);
        UserDAO ud = new UserDAO(em);
        VenditoreDAO ved = new VenditoreDAO(em);
        ViaggioDAO vid = new ViaggioDAO(em);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Scegli il tipo di utente");
        System.out.println("1. Utente normale");
        System.out.println("2. Amministratore");
        System.out.println("0. Esci");

        String action;

        do {
            String input = scanner.nextLine();
            action = input;
            switch (input) {
                case "1":
                    do {
                        System.out.println("Scegli cosa vuoi fare");
                        System.out.println("1. Login");
                        System.out.println("2. Crea un nuovo utente");
                        System.out.println("0. Esci");
                        action = scanner.nextLine();
                        switch (action) {
                            case "1":
                                System.out.println("Inserisci la tua email");
                                String loginEmail = scanner.nextLine();
                                System.out.println("Inserisci la tua password");
                                String loginPassword = scanner.nextLine();
                                User loggedInUser = ud.findUserByEmailAndPassword(loginEmail, loginPassword);
                                System.out.println("Benvenuto/a " + loggedInUser.getNome());
                                System.out.println("Scegli un'azione");
                                System.out.println("1. Compra un biglietto");
                                System.out.println("2. Compra un abbonamento");
                                System.out.println("3. Effettua un viaggio");
                                System.out.println("0. Esci");
                                action = scanner.nextLine();
                                switch (action) {
                                    case "1":
                                        List<Venditore> listaVenditori = ved.findAllVenditoriAttivi();
                                        for (int i = 0; i < listaVenditori.size(); i++) {
                                            System.out.println(i + 1 + ". " + listaVenditori.get(i));
                                        }
                                        System.out.println("Scegli un venditore tra quelli nella lista");
                                        String inputVenditore = scanner.nextLine();
                                        Venditore venditoreScelto = listaVenditori.get(Integer.parseInt(inputVenditore) - 1);
                                        Biglietto nuovoBiglietto = venditoreScelto.emettiBiglietto(LocalDate.now().toString(), loggedInUser.getTessera());
                                        bd.save(nuovoBiglietto);
                                }
                                break;
                        }
                    } while (!action.equals("0"));
                    break;
                case "2":
                    System.out.println("Inserisci password");
                    break;
                default:
                    if (!action.equals("0")) {
                        System.out.println("Scegli un'opzione valida");
                        System.out.println("1. Utente normale");
                        System.out.println("2. Amministratore");
                        System.out.println("0. Esci");
                    } else {
                        break;
                    }
            }
        } while (!action.equals("0"));




        /*User sergioMattarella = new User("Sergio", "Mattarella", "1938-05-12");
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
        //System.out.println(bd.countBigliettiTimbratiInPeriodoDiTempo("2024-06-27", "2024-06-28"));

        //System.out.println(vid.countBigliettiTimbratiByMezzo("6451bf53-c52c-455e-ab22-48bb75f1bc6a"));
        Biglietto b1 = bd.findById("3f7e4b1b-7e36-44aa-b6bb-59b609f73753");
        Viaggio v2 = vid.findViaggioById("b02d9c25-cc45-457f-ae0f-1a36c9262128");

        System.out.println(bd.countBigliettiTimbratiByMezzo("6451bf53-c52c-455e-ab22-48bb75f1bc6a"));*/

        em.close();
        emf.close();
    }
}
