package aalbertocoscia.entities;

import aalbertocoscia.enums.StatoMezzo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Tram")
public class Tram extends Mezzo {

    public Tram(int capienza, StatoMezzo statoMezzo) {
        super(capienza, statoMezzo);
    }

    public Tram() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
