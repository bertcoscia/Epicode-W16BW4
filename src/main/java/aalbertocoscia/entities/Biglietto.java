package aalbertocoscia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends TitoloViaggio {
    private boolean timbrato;
    private LocalDate dataVidimazione;
    private double prezzo;

    @ManyToOne
    @JoinColumn(name = "id_viaggio")
    private Viaggio viaggio;

    public Biglietto() {
    }

    public Biglietto(String data_emissione, Venditore venditore) {
        super(data_emissione, venditore);
        this.prezzo = 1.50;
        this.timbrato = false;
    }

    public void timbraBiglietto(Viaggio viaggio) {
        this.timbrato = true;
        this.dataVidimazione = LocalDate.now();
        this.viaggio = viaggio;
        System.out.println("Biglietto timbrato correttamente");
    }


    public boolean isTimbrato() {
        return timbrato;
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
                "timbrato=" + timbrato +
                ", dataVidimazione=" + dataVidimazione +
                '}' + super.toString();
    }
}
