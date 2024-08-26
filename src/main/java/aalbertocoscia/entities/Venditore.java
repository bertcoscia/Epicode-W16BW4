package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "venditori")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_venditore", discriminatorType = DiscriminatorType.STRING)

public class Venditore {
    @Id
    @GeneratedValue
    private UUID idVenditore;
    private String indirizzo;

    public Venditore() {
    }

    public Venditore(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public UUID getIdVenditore() {
        return idVenditore;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return "Venditore{" +
                "idVenditore=" + idVenditore +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
