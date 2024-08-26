package aalbertocoscia.entities;

import aalbertocoscia.enums.StatoDistributoreAutomatico;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("distributore_automatico")

public class DistributoreAutomatico extends Venditore {
    @Enumerated(EnumType.STRING)
    private StatoDistributoreAutomatico stato;

    public DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String indirizzo, StatoDistributoreAutomatico stato) {
        super(indirizzo);
        this.stato = stato;
    }

    public StatoDistributoreAutomatico getStato() {
        return stato;
    }

    public void setStato(StatoDistributoreAutomatico stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{" +
                "dettagli=" + super.toString() +
                "stato=" + stato +
                '}';
    }
}
