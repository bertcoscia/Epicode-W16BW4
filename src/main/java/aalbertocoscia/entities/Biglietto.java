package aalbertocoscia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends TitoloViaggio {
    private boolean attivo;
    private LocalDate dataVidimazione;

    private double prezzo;

    public Biglietto() {
    }

    public Biglietto(String data_emissione) {
        super(data_emissione);
        this.prezzo = 1.50;
        this.attivo = true;
    }

    public void timbraBiglietto(boolean attivo, LocalDate dataVidimazione) {
        this.attivo = false;
        this.dataVidimazione = dataVidimazione;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDate dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    @Override
    public String toString() {
        return "Biglietti{" +
                "attivo=" + attivo +
                ", dataVidimazione=" + dataVidimazione +
                '}' + super.toString();
    }
}
