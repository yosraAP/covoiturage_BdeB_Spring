package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "reservation")

public class Reservation {
    @EmbeddedId
    private ReservationId idreservation;

    private Date dateReservation;
    private Integer nb_PlaceTotal;
    private float montant_a_payer;


    public Reservation() {
    }

    public Reservation(ReservationId idreservation, Date dateReservation, Integer nb_PlaceTotal, float montant_a_payer) {
        this.idreservation = idreservation;
        this.dateReservation = dateReservation;
        this.nb_PlaceTotal = nb_PlaceTotal;
        this.montant_a_payer = montant_a_payer;
    }

    public Reservation(Date dateReservation, Integer nb_PlaceTotal, float montant_a_payer) {
        this.dateReservation = dateReservation;
        this.nb_PlaceTotal = nb_PlaceTotal;
        this.montant_a_payer = montant_a_payer;
    }


    // Getter and setter for idreservation
    public ReservationId getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(ReservationId idreservation) {
        this.idreservation = idreservation;
    }


    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Integer getNb_PlaceTotal() {
        return nb_PlaceTotal;
    }

    public void setNb_PlaceTotal(Integer nb_PlaceTotal) {
        this.nb_PlaceTotal = nb_PlaceTotal;
    }

    public float getMontant_a_payer() {
        return montant_a_payer;
    }

    public void setMontant_a_payer(float montant_a_payer) {
        this.montant_a_payer = montant_a_payer;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idreservation=" + idreservation +
                ", dateReservation=" + dateReservation +
                ", nb_PlaceTotal=" + nb_PlaceTotal +
                ", montant_a_payer=" + montant_a_payer +
                '}';
    }
}

