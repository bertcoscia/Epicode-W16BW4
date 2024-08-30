package aalbertocoscia;

import aalbertocoscia.dao.*;
import aalbertocoscia.entities.*;
import aalbertocoscia.enums.DurataAbbonamento;
import aalbertocoscia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


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

        try {
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
                            System.out.println("Scegli un'opzione");
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
                                                System.out.println("Scegli un'opzione");
                                                System.out.println("1. Compra un biglietto");
                                                System.out.println("2. Compra un abbonamento");
                                                System.out.println("3. Controlla validita abbonamento");
                                                System.out.println("4. Vedi quanti biglietti disponibili sulla tua tessera");
                                                System.out.println("5. Timbra biglietto");
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
                                                    case "3": // Controlla validita abbonamento
                                                        try {
                                                            if (loggedInUser.isAbbonamentoValido()) {
                                                                System.out.println("Il tuo abbonamento è valido. Scadrà in data " + loggedInUser.getTessera().getAbbonamento().getData_scadenza().getDayOfMonth()
                                                                        + "-" + loggedInUser.getTessera().getAbbonamento().getData_scadenza().getMonthValue()
                                                                        + "-" + loggedInUser.getTessera().getAbbonamento().getData_scadenza().getYear());
                                                            } else {
                                                                System.out.println("Il tuo abbonamento è scaduto in data " + loggedInUser.getTessera().getAbbonamento().getData_scadenza().getDayOfMonth()
                                                                        + "-" + loggedInUser.getTessera().getAbbonamento().getData_scadenza().getMonthValue()
                                                                        + "-" + loggedInUser.getTessera().getAbbonamento().getData_scadenza().getYear());
                                                            }
                                                        } catch (RuntimeException e) {
                                                            System.err.println("Non hai un abbonamento");
                                                        }
                                                        break;
                                                    case "4": // Vedi quanti biglietti disponibili sulla tua tessera
                                                        Long countBigliettiDisponibiliByTessera = bd.countBigliettiDisponibiliByTessera(loggedInUser.getTessera().getIdTessera().toString());
                                                        System.out.println("Biglietti disponibili: " + countBigliettiDisponibiliByTessera);
                                                        break;
                                                    case "5": // Timbra biglietto
                                                        Long countBigliettiDisponibiliByTessera5 = bd.countBigliettiDisponibiliByTessera(loggedInUser.getTessera().getIdTessera().toString());
                                                        if (countBigliettiDisponibiliByTessera5 == 0) {
                                                            System.err.println("Non hai biglietti sulla tua tessera");
                                                            break;
                                                        } else {
                                                            System.out.println("Scegli il tuo viaggio");
                                                            List<Viaggio> listaViaggiTimbra = vid.findAllViaggi();
                                                            for (int i = 0; i < listaViaggiTimbra.size(); i++) {
                                                                System.out.println((i + 1) + ". " + listaViaggiTimbra.get(i));
                                                            }
                                                            try {
                                                                int indexListaViaggiTimbra = Integer.parseInt(scanner.nextLine());
                                                                if (indexListaViaggiTimbra <= listaViaggiTimbra.size()) {
                                                                    Viaggio viaggoTimbra = listaViaggiTimbra.get(indexListaViaggiTimbra - 1);
                                                                    List<Biglietto> listaBigliettiTimbra = bd.findAllBigliettiDisponibiliByTessera(loggedInUser.getTessera().getIdTessera().toString());
                                                                    Biglietto bigliettoDaTimbrare = listaBigliettiTimbra.getFirst();
                                                                    bigliettoDaTimbrare.timbraBiglietto(viaggoTimbra);
                                                                    bd.save(bigliettoDaTimbrare);
                                                                    Long countBigliettiRimanenti = bd.countBigliettiDisponibiliByTessera(loggedInUser.getTessera().getIdTessera().toString());
                                                                    System.out.println("Biglietti rimanenti: " + countBigliettiRimanenti);
                                                                } else {
                                                                    System.err.println("Seleziona un'opzione valida");
                                                                }
                                                            } catch (IllegalArgumentException e) {
                                                                System.err.println(e.getMessage());
                                                            }
                                                            break;
                                                        }
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
                                        User userDB = ud.findUserById(user.getIdUser().toString());
                                        userDB.setTessera(ted.findTesseraById(newTessera.getIdTessera().toString()));
                                        ud.save(userDB);

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
                            String adminAction;
                            do {
                                System.out.println("Scegli un'opzione");
                                System.out.println("1. Gestione parco mezzi");
                                System.out.println("2. Gestione tratte");
                                System.out.println("3. Gestione viaggi");
                                System.out.println("4. Gestione titoli di viaggio");
                                System.out.println("0. Esci");
                                adminAction = scanner.nextLine();
                                switch (adminAction) {
                                    case "1": // Gestione parco mezzi
                                        System.out.println("Scegli un'opzione");
                                        System.out.println("1. Vedi tutti i mezzi in servizio");
                                        System.out.println("2. Vedi tutti i mezzi in manutenzione");
                                        System.out.println("3. Aggiungi un nuovo mezzo");
                                        System.out.println("4. Modifica un mezzo esistente");
                                        System.out.println("5. Dai fuoco a un mezzo");
                                        System.out.println("6. Vedi tutte le manutenzioni");
                                        System.out.println("7. Inizia una nuova manutenzione");
                                        System.out.println("8. Modifica una manutenzione esistente");
                                        System.out.println("9. Termina una manutenzione");
                                        System.out.println("0. Torna al menu precedente");
                                        String adminInput1 = scanner.nextLine();
                                        switch (adminInput1) {
                                            case "1": // Vedi tutti i mezzi in servizio
                                                List<Mezzo> listaMezziServizio = med.getAllMezziInServizio();
                                                for (int i = 0; i < listaMezziServizio.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listaMezziServizio.get(i));
                                                }
                                                break;
                                            case "2": // Vedi tutti i mezzi in manutenzione
                                                List<Mezzo> listaMezziManutenzione = med.getAllMezziInManutenzione();
                                                for (int i = 0; i < listaMezziManutenzione.size(); i++) {
                                                    Manutenzione man = mad.findManutenzioneByMezzo(listaMezziManutenzione.get(i).getIdMezzo().toString());
                                                    System.out.println((i + 1) + ". " + listaMezziManutenzione.get(i) + " || " + man);
                                                }
                                                break;
                                            case "3": // Aggiungi un nuovo mezzo
                                                System.out.println("Inserisci la capienza del mezzo");
                                                int capienzaMezzo = Integer.parseInt(scanner.nextLine());
                                                String tipoMezzo;
                                                do {
                                                    System.out.println("Seleziona un'opzione");
                                                    System.out.println("1. Autobus");
                                                    System.out.println("2. Tram");
                                                    System.out.println("0. Torna indietro");
                                                    tipoMezzo = scanner.nextLine();
                                                    switch (tipoMezzo) {
                                                        case "1":
                                                            med.save(new Autobus(capienzaMezzo));
                                                            break;
                                                        case "2":
                                                            med.save(new Tram(capienzaMezzo));
                                                            break;
                                                        default:
                                                            if (!tipoMezzo.equals("0")) {
                                                                System.out.println("Scegli un'opzione valida");
                                                            }
                                                            break;
                                                    }
                                                } while (!tipoMezzo.equals("1") && !tipoMezzo.equals("2") && !tipoMezzo.equals("0"));
                                                break;
                                            case "4": // Modifica un mezzo esistente
                                                List<Mezzo> listMezziMod = med.getAllMezzi();
                                                for (int i = 0; i < listMezziMod.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listMezziMod.get(i));
                                                }
                                                System.out.println("Scegli il mezzo da modificare");
                                                int indexMezzoDaModificare = Integer.parseInt(scanner.nextLine());
                                                if (indexMezzoDaModificare <= listMezziMod.size()) {
                                                    Mezzo mezzoDaModificare = listMezziMod.get(indexMezzoDaModificare - 1);
                                                    System.out.println(mezzoDaModificare);
                                                    try {
                                                        System.out.println("Inserisci la capienza");
                                                        int capienzaMod = Integer.parseInt(scanner.nextLine());
                                                        mezzoDaModificare.setCapienza(capienzaMod);
                                                        med.save(mezzoDaModificare);
                                                    } catch (NotFoundException e) {
                                                        System.err.println(e.getMessage());
                                                    }
                                                }
                                                break;
                                            case "5": // Dai fuoco a un mezzo
                                                List<Mezzo> listMezzi = med.getAllMezzi();
                                                for (int i = 0; i < listMezzi.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listMezzi.get(i));
                                                }
                                                System.out.println("Scegli quale mezzo vuoi bruciare");
                                                int indexMezzoBruciare = Integer.parseInt(scanner.nextLine());
                                                if (indexMezzoBruciare <= listMezzi.size()) {
                                                    med.findMezzoByIdAndDelete(listMezzi.get(indexMezzoBruciare - 1).getIdMezzo().toString());
                                                } else {
                                                    System.out.println("Scegli un'opzione valida");
                                                }
                                                break;
                                            case "6":
                                                List<Manutenzione> listManutenzioni = mad.findAllManutenzioni();
                                                for (int i = 0; i < listManutenzioni.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listManutenzioni.get(i));
                                                }
                                                break;
                                            case "7": // Inizia una nuova manutenzione
                                                List<Mezzo> listMezziPerManutenzione = med.getAllMezziInServizio();
                                                for (int i = 0; i < listMezziPerManutenzione.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listMezziPerManutenzione.get(i));
                                                }
                                                System.out.println("Scegli il mezzo su cui intervenire");
                                                int indexMezzoDaAggiustare = Integer.parseInt(scanner.nextLine());
                                                if (indexMezzoDaAggiustare <= listMezziPerManutenzione.size()) {
                                                    System.out.println("Scrivi motivo della manutenzione");
                                                    String motivoManutenzione = scanner.nextLine();
                                                    Manutenzione manutenzione = listMezziPerManutenzione.get(indexMezzoDaAggiustare - 1).iniziaManutenzione(motivoManutenzione);
                                                    mad.save(manutenzione);
                                                } else {
                                                    System.out.println("Scegli un'opzione valida");
                                                }
                                                break;
                                            case "8": // Modifica una manutenzione esistente 8a9cb640-55bf-416a-88f8-5c0d65b3e88b
                                                List<Manutenzione> listManutenzioniMod = mad.findAllManutenzioni();
                                                System.out.println("Scegli la manutenzione da modificare");
                                                for (int i = 0; i < listManutenzioniMod.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listManutenzioniMod.get(i));
                                                }
                                                int indexManutenzioneMod = Integer.parseInt(scanner.nextLine());
                                                if (indexManutenzioneMod <= listManutenzioniMod.size()) {
                                                    Manutenzione manutenzioneDaModificare = listManutenzioniMod.get(indexManutenzioneMod - 1);
                                                    System.out.println(manutenzioneDaModificare);
                                                    System.out.println("Inserisci la data di inizio in questo formato YYYY-MM-DD");
                                                    String dataInizioMod = scanner.nextLine();
                                                    System.out.println("Inserisci il motivo della manutenzione");
                                                    String motivoMod = scanner.nextLine();
                                                    if (manutenzioneDaModificare.getDataFine() != null) {
                                                        System.out.println("Inserisci la data di fine in questo formato YYYY-MM-DD");
                                                        String dataFineMod = scanner.nextLine();
                                                        try {
                                                            manutenzioneDaModificare.setDataInizio(LocalDate.parse(dataInizioMod));
                                                            manutenzioneDaModificare.setDataFine(LocalDate.parse(dataFineMod));
                                                            manutenzioneDaModificare.setMotivo(motivoMod);
                                                            mad.save(manutenzioneDaModificare);
                                                        } catch (NotFoundException e) {
                                                            System.err.println(e.getMessage());
                                                        }
                                                    } else { // Se dataFine == null
                                                        try {
                                                            manutenzioneDaModificare.setDataInizio(LocalDate.parse(dataInizioMod));
                                                            manutenzioneDaModificare.setMotivo(motivoMod);
                                                            System.out.println("Vuoi aggiungere una data di fine manutenzione? Y/N");
                                                            String yesOrNot = scanner.nextLine();
                                                            if (yesOrNot.equalsIgnoreCase("y")) {
                                                                System.out.println("Inserisci la data di fine manutenzione in questo formato YYYY-DD-MM");
                                                                String dataFine = scanner.nextLine();
                                                                manutenzioneDaModificare.terminaManutenzione(dataFine);
                                                                try {
                                                                    mad.save(manutenzioneDaModificare);
                                                                } catch (NotFoundException e) {
                                                                    System.err.println(e.getMessage());
                                                                }
                                                            } else if (yesOrNot.equalsIgnoreCase("n")) {
                                                                try {
                                                                    mad.save(manutenzioneDaModificare);
                                                                } catch (NotFoundException e) {
                                                                    System.err.println(e.getMessage());
                                                                }
                                                            } else {
                                                                System.out.println("Scegli un'opzione valida");
                                                            }
                                                        } catch (NotFoundException e) {
                                                            System.err.println(e.getMessage());
                                                        }
                                                    }
                                                }
                                                break;
                                            case "9": // Termina una manutenzione
                                                List<Manutenzione> listManutenzioniInCorso = mad.findAllManutenzioniInCorso();
                                                for (int i = 0; i < listManutenzioniInCorso.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listManutenzioniInCorso.get(i));
                                                }
                                                if (!listManutenzioniInCorso.isEmpty()) {
                                                    System.out.println("Scegli la manutenzione da terminare");
                                                    int indexManutenzioneDaTerminare = Integer.parseInt(scanner.nextLine());
                                                    if (indexManutenzioneDaTerminare <= listManutenzioniInCorso.size()) {
                                                        Manutenzione man1 = listManutenzioniInCorso.get(indexManutenzioneDaTerminare - 1);
                                                        man1.terminaManutenzione();
                                                        mad.save(man1);
                                                    } else {
                                                        System.out.println("Scegli un'opzione valida");
                                                    }
                                                } else {
                                                    System.out.println("Non ci sono interventi di manutenzione in corso");
                                                }
                                                break;
                                            default:
                                                if (!adminInput1.equals("0")) {
                                                    System.out.println("Scegli un'opzione valida");
                                                    System.out.println("1. Vedi tutti i mezzi in servizio");
                                                    System.out.println("2. Vedi tutti i mezzi in manutenzione");
                                                    System.out.println("3. Aggiungi un nuovo mezzo");
                                                    System.out.println("4. Inizia una nuova manutenzione");
                                                    System.out.println("0. Torna al menu precedente");
                                                } else {
                                                    break;
                                                }
                                        }
                                        break;
                                    case "2": // Gestione tratte
                                        System.out.println("Scegli un'opzione");
                                        System.out.println("1. Vedi tutte le tratte");
                                        System.out.println("2. Crea una nuova tratta");
                                        System.out.println("3. Modifica una tratta esistente");
                                        System.out.println("4. Calcola i tempi di percorrenza media di una tratta");
                                        System.out.println("5. Elimina una tratta");
                                        System.out.println("0. Torna al menu precedente");
                                        String adminInput2 = scanner.nextLine();
                                        switch (adminInput2) {
                                            case "1": // Vedi tutte le tratte
                                                List<Tratta> listTratte = trd.findAllTratte();
                                                for (int i = 0; i < listTratte.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listTratte.get(i));
                                                }
                                                break;
                                            case "2": // Crea una nuova tratta
                                                System.out.println("Inserisci il numero di linea");
                                                String numeroLinea = scanner.nextLine();
                                                System.out.println("Inserisci l'indirizzo di partenza");
                                                String zonaPartenza = scanner.nextLine();
                                                System.out.println("Inserisci l'indirizzo del capolinea");
                                                String capolinea = scanner.nextLine();
                                                System.out.println("Inserisci la durata prevista della tratta");
                                                int durataPrevista = Integer.parseInt(scanner.nextLine());
                                                try {
                                                    Tratta tratta = new Tratta(numeroLinea, zonaPartenza, capolinea, durataPrevista);
                                                    trd.save(tratta);
                                                } catch (IllegalArgumentException e) {
                                                    System.err.println(e.getMessage());
                                                }
                                                break;
                                            case "3": // Modifica una tratta esistente
                                                List<Tratta> listTratteMod = trd.findAllTratte();
                                                for (int i = 0; i < listTratteMod.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listTratteMod.get(i));
                                                }
                                                int trattaMod = Integer.parseInt(scanner.nextLine());
                                                if (trattaMod <= listTratteMod.size()) {
                                                    try {
                                                        Tratta trattaDaModificare = listTratteMod.get(trattaMod - 1);
                                                        System.out.println(trattaDaModificare);
                                                        System.out.println("Inserisci il numero di linea");
                                                        String numeroLineaMod = scanner.nextLine();
                                                        System.out.println("Inserisci l'indirizzo di partenza");
                                                        String zonaPartenzaMod = scanner.nextLine();
                                                        System.out.println("Inserisci l'indirizzo del capolinea");
                                                        String capolineaMod = scanner.nextLine();
                                                        System.out.println("Inserisci la durata prevista della tratta");
                                                        int durataPrevistaMod = Integer.parseInt(scanner.nextLine());
                                                        trattaDaModificare.setNumeroLinea(numeroLineaMod);
                                                        trattaDaModificare.setZonaPartenza(zonaPartenzaMod);
                                                        trattaDaModificare.setCapolinea(capolineaMod);
                                                        trattaDaModificare.setDurataPrevista(durataPrevistaMod);
                                                        trd.save(trattaDaModificare);
                                                    } catch (NotFoundException e) {
                                                        System.err.println(e.getMessage());
                                                    }
                                                }
                                                break;
                                            case "4": // Calcola i tempi di percorrenza media di una tratta
                                                System.out.println("Seleziona una tratta");
                                                List<Tratta> listTratteAvg = trd.findAllTratte();
                                                for (int i = 0; i < listTratteAvg.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listTratteAvg.get(i));
                                                }
                                                int trattaAvg = Integer.parseInt(scanner.nextLine());
                                                Double avg = vid.avgPercorrenzaEffettivaByTratta(listTratteAvg.get(trattaAvg - 1).getIdTratta().toString());
                                                if (!avg.equals(0.0)) {
                                                    System.out.println("Durata prevista: " + listTratteAvg.get(trattaAvg - 1).getDurataPrevista() + " minuti, durata media effettiva: " + avg + " minuti");
                                                } else {
                                                    System.out.println("Non sono stati ancora effettuati viaggi per questa tratta");
                                                }
                                                break;
                                            case "5": // Elimina una tratta
                                                System.out.println("Seleziona una tratta");
                                                List<Tratta> listaTratteDelete = trd.findAllTratte();
                                                for (int i = 0; i < listaTratteDelete.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listaTratteDelete.get(i));
                                                }
                                                int trattaDaCancellare = Integer.parseInt(scanner.nextLine());
                                                if (trattaDaCancellare <= listaTratteDelete.size()) {
                                                    try {
                                                        trd.findTrattaByIdAndDelete(listaTratteDelete.get(trattaDaCancellare - 1).getIdTratta().toString());
                                                    } catch (NotFoundException e) {
                                                        System.err.println(e.getMessage());
                                                    }
                                                } else {
                                                    System.out.println("Scegli un'opzione valida");
                                                }
                                                break;
                                        }
                                        break;
                                    case "3": // Gestione viaggi
                                        System.out.println("Scegli un'opzione");
                                        System.out.println("1. Crea un nuovo viaggio");
                                        System.out.println("2. Vedi tutti i viaggi");
                                        System.out.println("3. Vedi tutti i viaggi per tratta");
                                        System.out.println("4. Conta il numero di viaggi di ogni mezzo per una determinata tratta");
                                        System.out.println("0. Torna al menu precedente");
                                        String adminInput3 = scanner.nextLine();
                                        switch (adminInput3) {
                                            case "1": // Crea un nuovo viaggio
                                                System.out.println("Scegli la tratta");
                                                List<Tratta> listTratteNewViaggio = trd.findAllTratte();
                                                for (int i = 0; i < listTratteNewViaggio.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listTratteNewViaggio.get(i));
                                                }
                                                try {
                                                    int indexTrattaNewViaggio = Integer.parseInt(scanner.nextLine());
                                                    if (indexTrattaNewViaggio <= listTratteNewViaggio.size()) {
                                                        Tratta trattaNewViaggio = listTratteNewViaggio.get(indexTrattaNewViaggio - 1);
                                                        System.out.println("Scegli il mezzo");
                                                        List<Mezzo> listMezziNewViaggio = med.getAllMezziInServizio();
                                                        for (int i = 0; i < listMezziNewViaggio.size(); i++) {
                                                            System.out.println((i + 1) + ". " + listMezziNewViaggio.get(i));
                                                        }
                                                        try {
                                                            int indexMezzoNewViaggio = Integer.parseInt(scanner.nextLine());
                                                            if (indexMezzoNewViaggio <= listMezziNewViaggio.size()) {
                                                                Mezzo mezzoNewViaggio = listMezziNewViaggio.get(indexMezzoNewViaggio - 1);
                                                                System.out.println("Inserisci la durata effettiva del viaggio");
                                                                try {
                                                                    int durataEffettivaNewViaggio = Integer.parseInt(scanner.nextLine());
                                                                    try {
                                                                        Viaggio newViaggio = new Viaggio(durataEffettivaNewViaggio, mezzoNewViaggio, trattaNewViaggio);
                                                                        vid.save(newViaggio);
                                                                    } catch (IllegalArgumentException e) {
                                                                        System.err.println(e.getMessage());
                                                                    }
                                                                } catch (IllegalArgumentException e) {
                                                                    System.err.println(e.getMessage());
                                                                }
                                                            } else {
                                                                System.err.println("Scegli un'opzione valida");
                                                            }
                                                        } catch (IllegalArgumentException e) {
                                                            System.err.println(e.getMessage());
                                                        }
                                                    } else {
                                                        System.err.println("Scegli un'opzione valida");
                                                    }
                                                } catch (IllegalArgumentException e) {
                                                    System.err.println(e.getMessage());
                                                }
                                                break;
                                            case "2": // Vedi tutti i viaggi
                                                List<Viaggio> listViaggi = vid.findAllViaggi();
                                                for (int i = 0; i < listViaggi.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listViaggi.get(i));
                                                }
                                                break;
                                            case "3": // Vedi tutti i viaggi per tratta
                                                System.out.println("Scegli la tratta che vuoi controllare");
                                                List<Tratta> listTratta1 = trd.findAllTratte();
                                                for (int i = 0; i < listTratta1.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listTratta1.get(i));
                                                }
                                                try {
                                                    int indexListTratta1 = Integer.parseInt(scanner.nextLine());
                                                    if (indexListTratta1 <= listTratta1.size()) {
                                                        Tratta tratta1 = listTratta1.get(indexListTratta1 - 1);
                                                        try {
                                                            List<Viaggio> listViaggiByTratta = vid.findViaggiByTratta(tratta1.getIdTratta().toString());
                                                            if (!listViaggiByTratta.isEmpty()) {
                                                                for (int i = 0; i < listViaggiByTratta.size(); i++) {
                                                                    System.out.println((i + 1) + ". " + listViaggiByTratta.get(i));
                                                                }
                                                            } else {
                                                                System.out.println("Non sono stati effettuati viaggi per questa tratta");
                                                            }
                                                        } catch (NotFoundException e) {
                                                            System.err.println(e.getMessage());
                                                        }
                                                    }
                                                } catch (IllegalArgumentException e) {
                                                    System.err.println(e.getMessage());
                                                }
                                                break;
                                            case "4": // Conta il numero di viaggi di ogni mezzo per una determinata tratta
                                                List<Tratta> listTratteCase4 = trd.findAllTratte();
                                                System.out.println("Scegli la tratta");
                                                for (int i = 0; i < listTratteCase4.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listTratteCase4.get(i));
                                                }
                                                try {
                                                    int indexCase4 = Integer.parseInt(scanner.nextLine());
                                                    if (indexCase4 <= listTratteCase4.size()) {
                                                        Tratta tratta = listTratteCase4.get(indexCase4 - 1);
                                                        List<Viaggio> listViaggiPerTratta = (vid.findViaggiByTratta(tratta.getIdTratta().toString()));
                                                        Map<String, Long> viaggiPerMezzoMap = listViaggiPerTratta.stream()
                                                                .collect(Collectors.groupingBy(
                                                                        viaggio -> viaggio.getMezzo().getIdMezzo().toString(),
                                                                        Collectors.counting()
                                                                ));
                                                        for (Map.Entry<String, Long> entry : viaggiPerMezzoMap.entrySet()) {
                                                            System.out.println("Tratta " + tratta.getNumeroLinea() + " -> Mezzo ID: " + entry.getKey() + " -> Numero di viaggi: " + entry.getValue());
                                                        }
                                                    } else {
                                                        System.err.println("Scegli un'opzione valida");
                                                    }
                                                } catch (IllegalArgumentException e) {
                                                    System.err.println(e.getMessage());
                                                }
                                                break;
                                        }
                                        break;
                                    case "4": // Gestione titoli di viaggio
                                        System.out.println("Scegli un'opzione");
                                        System.out.println("1. Conta titoli di viaggio emessi in totale");
                                        System.out.println("2. Conta titoli di viaggio emessi in totale per uno specifico intervallo di tempo");
                                        System.out.println("3. Conta titoli di viaggio emessi per punto vendita");
                                        System.out.println("4. Conta titoli di viaggio emessi per punto vendita in uno specifico intervallo di tempo per punto vendita");
                                        System.out.println("5. Conta biglietti timbrati per tratta");
                                        System.out.println("0. Torna al menu precedente");
                                        String adminInput4 = scanner.nextLine();
                                        switch (adminInput4) {
                                            case "1": // Conta titoli di viaggio emessi in totale
                                                Long countTitoliViaggio1 = tvd.countAllTitoliViaggio();
                                                System.out.println("Biglietti emessi in totale: " + countTitoliViaggio1);
                                                break;
                                            case "2": // Conta titoli di viaggio emessi in totale per uno specifico intervallo di tempo
                                                break;
                                            case "3": // Conta titoli di viaggio emessi per punto vendita
                                                System.out.println("Scegli il venditore");
                                                List<Venditore> listVenditoriCount1 = ved.findAllVenditori();
                                                for (int i = 0; i < listVenditoriCount1.size(); i++) {
                                                    System.out.println((i + 1) + ". " + listVenditoriCount1.get(i));
                                                }
                                                try {
                                                    int indexListVenditoriCount1 = Integer.parseInt(scanner.nextLine());
                                                    if (indexListVenditoriCount1 <= listVenditoriCount1.size()) {
                                                        Venditore venditore1 = listVenditoriCount1.get(indexListVenditoriCount1 - 1);
                                                        int countBigliettiByVenditore1 = ved.countBigliettiByVenditore(venditore1.getIdVenditore().toString());
                                                        int countAbbonamentiByVenditore1 = ved.countAbbonamentiByVenditore(venditore1.getIdVenditore().toString());
                                                        Map<String, Integer> mapCount1 = new HashMap<>();
                                                        mapCount1.put("Biglietti", countBigliettiByVenditore1);
                                                        mapCount1.put("Abbonamenti", countAbbonamentiByVenditore1);
                                                        System.out.println("Venditore n. " + venditore1.getIdVenditore().toString());
                                                        System.out.println(mapCount1.entrySet());
                                                    } else {
                                                        System.err.println("Scegli un'opzione valida");
                                                    }
                                                } catch (IllegalArgumentException e) {
                                                    System.err.println(e.getMessage());
                                                }
                                                break;
                                            case "4": // Conta titoli di viaggio emessi per punto vendita in uno specifico intervallo di tempo per punto vendita
                                                break;
                                            case "5": // Conta biglietti timbrati per tratta
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    default:
                                        if (!adminAction.equals("0")) {
                                            System.out.println("Scegli un'opzione valida");
                                        } else {
                                            break;
                                        }
                                }
                            } while (!adminAction.equals("0"));


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
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

        em.close();
        emf.close();
    }
}
