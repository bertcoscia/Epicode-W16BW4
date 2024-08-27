package aalbertocoscia.entities;

import aalbertocoscia.enums.DurataAbbonamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends TitoloViaggio {

    @Enumerated(EnumType.STRING)
    private DurataAbbonamento durata;
    private LocalDate data_scadenza;
    private double prezzo;


    public Abbonamento() {
    }

    public Abbonamento(String data_emissione, DurataAbbonamento durata) {
        super(data_emissione);
        this.durata = durata;
        this.data_scadenza = this.durata == DurataAbbonamento.SETTIMANALE ? this.getData_emissione().plusDays(6) : this.getData_emissione().plusMonths(1);
        this.prezzo = this.durata == DurataAbbonamento.SETTIMANALE ? 20.50 : 38;
    }

    public DurataAbbonamento getDurata() {
        return durata;
    }

    public void setDurata(DurataAbbonamento durata) {
        this.durata = durata;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    @Override
    public String toString() {
        return "Abbonamenti{" +
                "durata=" + durata +
                ", data_scadenza=" + data_scadenza +
                "} " + super.toString();
    }
}
