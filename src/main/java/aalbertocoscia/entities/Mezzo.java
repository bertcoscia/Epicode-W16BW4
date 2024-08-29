package aalbertocoscia.entities;

import aalbertocoscia.enums.StatoMezzo;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_mezzo", discriminatorType = DiscriminatorType.STRING)

public class Mezzo {

    @Id
    @GeneratedValue
    @Column(name = "id_mezzo")
    private UUID idMezzo;
    private int capienza;
    @Enumerated(EnumType.STRING)
    @Column(name = "stato_mezzo")
    private StatoMezzo statoMezzo;

    @OneToMany(mappedBy = "mezzo")
    private List<Viaggio> listViaggi;

    public Mezzo(int capienza, StatoMezzo statoMezzo) {
        this.capienza = capienza;
        this.statoMezzo = statoMezzo;
    }

    public Mezzo() {
    }

    public UUID getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(UUID idMezzo) {
        this.idMezzo = idMezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    @Override
    public String toString() {
        return "Mezzo n. " + idMezzo + ". Capienza: " + capienza + ". Stato: " + statoMezzo;
    }
}
