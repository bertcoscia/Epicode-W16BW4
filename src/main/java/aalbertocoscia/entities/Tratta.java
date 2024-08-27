package aalbertocoscia.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue
    @Column(name = "id_tratta")
    private UUID idTratta;
    @Column(name = "numero_linea")
    private String numeroLinea;
    @Column(name = "zona_di_partenza")
    private String zonaPartenza;
    private String capolinea;
    @Column(name = "durata_prevista")
    private int durataPrevista;

    public Tratta() {
    }

    public Tratta(String numeroLinea, String zonaPartenza, String capolinea, int durataPrevista) {
        this.numeroLinea = numeroLinea;
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.durataPrevista = durataPrevista;
    }

    public UUID getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(UUID idTratta) {
        this.idTratta = idTratta;
    }

    public String getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getDurataPrevista() {
        return durataPrevista;
    }

    public void setDurataPrevista(int durataPrevista) {
        this.durataPrevista = durataPrevista;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "idTratta=" + idTratta +
                ", numeroLinea='" + numeroLinea + '\'' +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", durataPrevista=" + durataPrevista +
                '}';
    }
}
