package aalbertocoscia.entities;

import aalbertocoscia.enums.StatoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "manutenzioni")
public class Manutenzione {
    @Id
    @GeneratedValue
    @Column(name = "id_manutenzione")
    private UUID idManutenzione;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    @Column(name = "data_fine")
    private LocalDate dataFine;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    public Manutenzione() {
    }

    public Manutenzione(String dataInizio, String motivo, Mezzo mezzo) {
        this.dataInizio = LocalDate.parse(dataInizio);
        this.dataFine = null;
        this.motivo = motivo;
        this.mezzo = mezzo;
    }

    public Manutenzione terminaManutenzione() {
        this.getMezzo().setStatoMezzo(StatoMezzo.IN_SERVIZIO);
        this.dataFine = LocalDate.now();
        return this;
    }

    public Manutenzione terminaManutenzione(String dataFine) {
        this.getMezzo().setStatoMezzo(StatoMezzo.IN_SERVIZIO);
        this.dataFine = LocalDate.parse(dataFine);
        return this;
    }

    public UUID getIdManutenzione() {
        return idManutenzione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Manutenzione n." + idManutenzione + ", mezzo n. " + mezzo.getIdMezzo() + ". Data di inizio: " + dataInizio + ", data di fine: " + dataFine + ". Motivo: " + motivo;
    }
}
