package aalbertocoscia.entities;

import aalbertocoscia.enums.DurataAbbonamento;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "venditori")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_venditore", discriminatorType = DiscriminatorType.STRING)

public class Venditore {
    @Id
    @GeneratedValue
    @Column(name = "id_venditore", unique = true)
    private UUID idVenditore;
    private String indirizzo;

    @OneToMany(mappedBy = "venditore", orphanRemoval = true)
    private List<Biglietto> listBiglietti;

    public Venditore() {
    }

    public Venditore(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Biglietto emettiBiglietto(String data_emissione, Tessera tessera) {
        return new Biglietto(data_emissione, this, tessera);
    }

    public Abbonamento emettiAbbonamento(String data_emissione, DurataAbbonamento durata, Tessera tessera) {
        return new Abbonamento(data_emissione, this, durata, tessera);
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
        return ", indirizzo: " + indirizzo;
    }
}
