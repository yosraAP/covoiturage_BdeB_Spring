package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable  //Utiliser quand il ya plusieur cle permiere
public class ReservationId implements Serializable {

    @OneToOne
    @JoinColumn(name = "id_passager")
    private Passager idPassager;
    @OneToOne
    @JoinColumn(name = "id_trajet")
    private Trajet idTrajet;

    public ReservationId() {
    }

    public ReservationId(Passager idPassager, Trajet idTrajet) {
        this.idPassager = idPassager;
        this.idTrajet = idTrajet;
    }

    public Passager getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(Passager idPassager) {
        this.idPassager = idPassager;
    }

    public Trajet getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Trajet idTrajet) {
        this.idTrajet = idTrajet;
    }

    @Override
    public boolean equals(Object o) {
//        return super.equals(obj);

        if (this == o) return true; if (o == null || getClass() != o.getClass())
            return false;
        ReservationId  reservationId = (ReservationId) o;
        return idPassager.equals(reservationId.idPassager) && idTrajet.equals(reservationId.idTrajet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPassager, idTrajet);
    }
}
