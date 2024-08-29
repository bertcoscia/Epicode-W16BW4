package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue
    private UUID idViaggio;

    @Column(name = "durata_effettiva")
    private int durataEffettiva;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "id_tratta", nullable = false)
    private Tratta tratta;

    @OneToMany(mappedBy = "viaggio", cascade = CascadeType.ALL)
    private List<Biglietto> listBiglietti;

    public Viaggio() {
    }

    public Viaggio(int durataEffettiva, Mezzo mezzo, Tratta tratta) {
        this.durataEffettiva = durataEffettiva;
        this.mezzo = mezzo;
        this.tratta = tratta;
    }

    public UUID getIdViaggio() {
        return idViaggio;
    }

    public int getDurataEffettiva() {
        return durataEffettiva;
    }

    public void setDurataEffettiva(int durataEffettiva) {
        this.durataEffettiva = durataEffettiva;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Viaggio{" +
                "idViaggio=" + idViaggio +
                ", durataEffettiva=" + durataEffettiva +
                ", mezzo=" + mezzo +
                ", tratta=" + tratta +
                '}';
    }
}
