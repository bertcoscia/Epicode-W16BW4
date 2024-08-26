package aalbertocoscia.entities;

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

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    public Manutenzione() {
    }

    public Manutenzione(UUID idManutenzione, LocalDate dataInizio, LocalDate dataFine) {
        this.idManutenzione = idManutenzione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
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

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "idManutenzione=" + idManutenzione +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
