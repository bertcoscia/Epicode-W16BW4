package aalbertocoscia.entities;

import eunms.StatoMezzo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Autobus")
public class Autobus extends Mezzo {

    public Autobus(int capienza, StatoMezzo statoMezzo) {
        super(capienza, statoMezzo);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
