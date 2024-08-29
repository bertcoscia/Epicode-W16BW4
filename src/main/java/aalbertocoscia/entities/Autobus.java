package aalbertocoscia.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Autobus")
public class Autobus extends Mezzo {

    public Autobus(int capienza) {
        super(capienza);
    }

    public Autobus() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
