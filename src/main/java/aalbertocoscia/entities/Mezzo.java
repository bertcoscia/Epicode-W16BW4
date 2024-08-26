package aalbertocoscia.entities;

import eunms.StatoMezzo;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_mezzo", discriminatorType = DiscriminatorType.STRING)

public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mezzo")
    private UUID idMezzo;
    private int capienza;
    @Column(name = "stato_mezzo")
    private StatoMezzo statoMezzo;

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

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "idMezzo=" + idMezzo +
                ", capienza=" + capienza +
                ", statoMezzo=" + statoMezzo +
                '}';
    }
}
