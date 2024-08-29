package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.*;
import aalbertocoscia.enums.DurataAbbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

                                // Validazione input
                                if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
                                    System.out.println("Email e password non possono essere vuoti.");
                                    break;
                                }

                                try {
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
                                                    try {
                                                        System.out.println("Scegli un venditore tra quelli nella lista");
                                                        String inputVenditore1 = scanner.nextLine();
                                                        int venditoreIndex1 = Integer.parseInt(inputVenditore1) - 1;
                                                        if (venditoreIndex1 < 0 || venditoreIndex1 >= listaVenditori1.size()) {
                                                            throw new IndexOutOfBoundsException();
                                                        }
                                                        Venditore venditoreScelto1 = listaVenditori1.get(venditoreIndex1);
                                                        Biglietto nuovoBiglietto = venditoreScelto1.emettiBiglietto(LocalDate.now().toString(), tesseraLoggedInUser);
                                                        bd.save(nuovoBiglietto);
                                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                        System.out.println("Errore: Seleziona un venditore valido.");
                                                    }
                                                    break;
                                                case "2": // Compra un abbonamento
                                                    List<Venditore> listaVenditori2 = ved.findAllVenditoriAttivi();
                                                    for (int i = 0; i < listaVenditori2.size(); i++) {
                                                        System.out.println((i + 1) + ". " + listaVenditori2.get(i));
                                                    }
                                                    try {
                                                        System.out.println("Scegli un venditore tra quelli nella lista");
                                                        String inputVenditore2 = scanner.nextLine();
                                                        int venditoreIndex2 = Integer.parseInt(inputVenditore2) - 1;
                                                        if (venditoreIndex2 < 0 || venditoreIndex2 >= listaVenditori2.size()) {
                                                            throw new IndexOutOfBoundsException();
                                                        }
                                                        Venditore venditoreScelto2 = listaVenditori2.get(venditoreIndex2);

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
                                                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                                        System.out.println("Errore: Seleziona un venditore valido.");
                                                    }
                                                    break;
                                            }
                                        } while (!userAction.equals("0"));
                                    } else {
                                        System.out.println("Email o password errati.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Errore: " + e.getMessage());
                                }
                                break;

                            case "2": // Crea nuovo utente
                                try {
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

                                    // Validazione input
                                    if (nomeUser.isEmpty() || cognomeUser.isEmpty() || dataNascita.isEmpty() || email.isEmpty() || password.isEmpty()) {
                                        System.out.println("Tutti i campi sono obbligatori.");
                                        break;
                                    }

                                    // Validazione data
                                    LocalDate birthDate = LocalDate.parse(dataNascita);

                                    User user = new User(nomeUser, cognomeUser, dataNascita, email, password);
                                    Tessera newTessera = user.creaTessera();
                                    ud.save(user);
                                    ted.save(newTessera);

                                    System.out.println("Utente creato con successo!");
                                } catch (DateTimeParseException e) {
                                    System.out.println("Errore: Formato della data non valido. Usa il formato YYYY-MM-DD.");
                                } catch (IllegalArgumentException e) {
                                    System.err.println("Errore: " + e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Errore imprevisto: " + e.getMessage());
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
                    String adminPassword = scanner.nextLine();


                    if (adminPassword.equals("admin123")) {
                        System.out.println("Accesso amministratore consentito.");


                    } else {
                        System.out.println("Password errata.");
                    }
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
