package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.*;
import aalbertocoscia.enums.DurataAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Epicode-W16BW4");

    public static void main(String[] args) {
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

        String action;

        do {
            // Menu principale
            System.out.println("Scegli il tipo di utente");
            System.out.println("1. Utente normale");
            System.out.println("2. Amministratore");
            System.out.println("0. Esci");

            action = scanner.nextLine();

            switch (action) {
                case "1":
                    String action2;
                    do {
                        // Sottomenu per utente normale
                        System.out.println("Scegli cosa vuoi fare");
                        System.out.println("1. Login");
                        System.out.println("2. Crea un nuovo utente");
                        System.out.println("0. Torna al menu precedente");

                        action2 = scanner.nextLine();

                        switch (action2) {
                            case "1": // Login
                                System.out.println("Inserisci la tua email");
                                String loginEmail = scanner.nextLine();
                                System.out.println("Inserisci la tua password");
                                String loginPassword = scanner.nextLine();
                                User loggedInUser = ud.findUserByEmailAndPassword(loginEmail, loginPassword);


                                if (loggedInUser != null) {
                                    System.out.println("Benvenuto/a " + loggedInUser.getNome());
                                    // Menu per utente loggato
                                    String userAction;
                                    do {
                                        System.out.println("Scegli un'azione");
                                        System.out.println("1. Compra un biglietto");
                                        System.out.println("2. Compra un abbonamento");
                                        System.out.println("0. Torna indietro");
                                        userAction = scanner.nextLine();

                                        Tessera tesseraLoggedInUser = loggedInUser.getTessera();
                                        switch (userAction) {
                                            case "1": // Compra un biglietto
                                                List<Venditore> listaVenditori1 = ved.findAllVenditoriAttivi();
                                                for (int i = 0; i < listaVenditori1.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listaVenditori1.get(i));
                                                }
                                                System.out.println("Scegli un venditore tra quelli nella lista");
                                                String inputVenditore1 = scanner.nextLine();
                                                Venditore venditoreScelto1 = listaVenditori1.get(Integer.parseInt(inputVenditore1) - 1);
                                                Biglietto nuovoBiglietto = venditoreScelto1.emettiBiglietto(LocalDate.now().toString(), tesseraLoggedInUser);
                                                bd.save(nuovoBiglietto);
                                                break;
                                            case "2": // Compra un abbonamento
                                                List<Venditore> listaVenditori2 = ved.findAllVenditoriAttivi();
                                                for (int i = 0; i < listaVenditori2.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listaVenditori2.get(i));
                                                }
                                                System.out.println("Scegli un venditore tra quelli nella lista");
                                                String inputVenditore2 = scanner.nextLine();
                                                Venditore venditoreScelto2 = listaVenditori2.get(Integer.parseInt(inputVenditore2) - 1);

                                                String durataAbbonamento;
                                                do {
                                                    System.out.println("Scegli la durata dell'abbonamento");
                                                    System.out.println("1. Settimanale");
                                                    System.out.println("2. Mensile");
                                                    System.out.println("0. Torna indietro");
                                                    durataAbbonamento = scanner.nextLine();

                                                    switch (durataAbbonamento) {
                                                        case "1": //Abbonamento settimanale
                                                            Abbonamento newAbbonamentoSettimanale = venditoreScelto2.emettiAbbonamento(LocalDate.now().toString(), DurataAbbonamento.SETTIMANALE, tesseraLoggedInUser);
                                                            ad.save(newAbbonamentoSettimanale);
                                                            tesseraLoggedInUser.setAbbonamento(newAbbonamentoSettimanale);
                                                            ted.save(tesseraLoggedInUser);
                                                            break;
                                                        case "2": //Abbonamento mensile
                                                            Abbonamento newAbbonamentoMensile = venditoreScelto2.emettiAbbonamento(LocalDate.now().toString(), DurataAbbonamento.MENSILE, tesseraLoggedInUser);
                                                            ad.save(newAbbonamentoMensile);
                                                            tesseraLoggedInUser.setAbbonamento(newAbbonamentoMensile);
                                                            ted.save(tesseraLoggedInUser);
                                                            break;
                                                        default:
                                                            if (!durataAbbonamento.equals("0")) {
                                                                System.out.println("Scegli un'opzione valida");
                                                            }
                                                    }
                                                } while (!durataAbbonamento.equals("0"));
                                                break;
                                        }
                                    } while (!userAction.equals("0"));
                                } else {
                                    System.out.println("Utente non trovato");
                                }
                                break;

                            case "2": // Crea nuovo utente
                                System.out.println("Inserisci il nome");
                                String nomeUser = scanner.nextLine();
                                System.out.println("Inserisci il cognome");
                                String cognomeUser = scanner.nextLine();
                                System.out.println("Inserisci la data di nascita in questo formato YYYY-MM-DD");
                                String dataNascita = scanner.nextLine();
                                System.out.println("Inserisci la tua email");
                                String email = scanner.nextLine();
                                System.out.println("Scegli una password");
                                String password = scanner.nextLine();
                                try {
                                    User user = new User(nomeUser, cognomeUser, dataNascita, email, password);
                                    Tessera newTessera = user.creaTessera();
                                    ud.save(user);
                                    ted.save(newTessera);
                                } catch (IllegalArgumentException e) {
                                    System.err.println(e.getMessage());
                                }
                                break;

                            case "0": // Torna al menu principale
                                System.out.println("Tornando al menu principale...");
                                break;

                            default:
                                System.out.println("Scegli un'opzione valida");
                                break;
                        }
                    } while (!action2.equals("0"));
                    break;

                case "2":
                    System.out.println("Inserisci password per amministratore");
                    break;

                case "0":
                    System.out.println("Arrivederci!");
                    break;

                default:
                    System.out.println("Scegli un'opzione valida");
                    break;
            }
        } while (!action.equals("0"));

        em.close();
        emf.close();
    }
}
