package aalbertocoscia.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Tram")
public class Tram extends Mezzo {

    public Tram(int capienza) {
        super(capienza);
    }

    public Tram() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
