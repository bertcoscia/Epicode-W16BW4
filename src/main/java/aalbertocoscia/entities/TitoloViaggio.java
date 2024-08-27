package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "titoli_di_viaggio")
public abstract class TitoloViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDate data_emissione;


    public TitoloViaggio(String data_emissione) {

        this.data_emissione = LocalDate.parse(data_emissione);

    }

    public TitoloViaggio() {

    }

    public UUID getId() {
        return id;
    }


    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }


    @Override
    public String toString() {
        return "TitoloViaggio{" +
                "id=" + id +
                ", data_emissione=" + data_emissione +

                '}';
    }
}
