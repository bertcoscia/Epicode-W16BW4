package aalbertocoscia.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Autobus")
public class Tram {

    public Tram() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
