package aalbertocoscia.entities;

import aalbertocoscia.enums.StatoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
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

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Viaggio> listViaggi;

    public Mezzo(int capienza) {
        this.capienza = capienza;
        this.statoMezzo = StatoMezzo.IN_SERVIZIO;
    }

    public Mezzo() {
    }

    public Manutenzione iniziaManutenzione(String motivo) {
        this.setStatoMezzo(StatoMezzo.IN_MANUTENZIONE);
        return new Manutenzione(LocalDate.now().toString(), motivo, this);
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
