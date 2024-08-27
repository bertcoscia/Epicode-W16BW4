package aalbertocoscia.entities;


import aalbertocoscia.enums.StatoMezzo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Autobus")
public class Autobus extends Mezzo {

    public Autobus(int capienza, StatoMezzo statoMezzo) {
        super(capienza, statoMezzo);
    }

    public Autobus() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
