package aalbertocoscia.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("rivenditore")
public class Rivenditore extends Venditore {
    private String nominativo;

    public Rivenditore() {
    }

    public Rivenditore(String indirizzo, String nominativo) {
        super(indirizzo);
        this.nominativo = nominativo;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "dettagli=" + super.toString() +
                "nominativo='" + nominativo + '\'' +
                '}';
    }
}
