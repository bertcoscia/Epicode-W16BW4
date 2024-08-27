package aalbertocoscia.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Biglietti extends TitoloViaggio {
    private boolean attivo;
    private LocalDate dataVidimazione;

    private double prezzo;

    public Biglietti() {
    }

    public Biglietti(String data_emissione) {
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
