package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue
    @Column(name = "id_tessera", unique = true)
    private UUID idTessera;
    @Column(name = "data_emissione")
    private LocalDate dataEmissione;
    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToOne
    @JoinColumn(name = "id_abbonamento")
    private Abbonamento abbonamento;


    public Tessera() {
    }

    public Tessera(User user, String dataEmissione) {
        this.dataEmissione = LocalDate.parse(dataEmissione);
        this.dataScadenza = this.dataEmissione.plusYears(1);
        this.user = user;
        this.abbonamento = null;
    }

    public Tessera(String dataEmissione, User user, Abbonamento abbonamento) {
        this.dataEmissione = LocalDate.parse(dataEmissione);
        this.dataScadenza = this.dataEmissione.plusYears(1);
        this.user = user;
        this.abbonamento = abbonamento;
    }

    public UUID getIdTessera() {
        return idTessera;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "idTessera=" + idTessera +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", user=" + user +
                '}';
    }
}
