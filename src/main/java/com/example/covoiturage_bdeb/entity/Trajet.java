package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trajet")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrajet")
    private Integer idTrajet;
    private Date dateTrajet;
    private String pointArrivee;
    private String pointDepart;
    private String heureDepart;
    private String heureArrivee;
    private float prixTrajet;
    private Integer nb_Placedisponible;

    @ManyToOne
    @JoinColumn(name = "id_conducteur", nullable = false)
    private Conducteur conducteur;

    @OneToMany(mappedBy = "Trajet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passager> passagers = new ArrayList<>();

    public Trajet() {
    }

    public Trajet(Date dateTrajet, String pointArrivee, String pointDepart, String heureDepart, String heureArrivee, float prixTrajet, Integer nb_Placedisponible, Conducteur conducteur) {
        this.dateTrajet = dateTrajet;
        this.pointArrivee = pointArrivee;
        this.pointDepart = pointDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.prixTrajet = prixTrajet;
        this.nb_Placedisponible = nb_Placedisponible;
        this.conducteur = conducteur;
    }

    public Trajet(Date dateTrajet, String pointArrivee, String pointDepart, String heureDepart, String heureArrivee, float prixTrajet, Integer nb_Placedisponible) {
        this.dateTrajet = dateTrajet;
        this.pointArrivee = pointArrivee;
        this.pointDepart = pointDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.prixTrajet = prixTrajet;
        this.nb_Placedisponible = nb_Placedisponible;
    }

    public Integer getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Integer idTrajet) {
        this.idTrajet = idTrajet;
    }

    public Date getDateTrajet() {
        return dateTrajet;
    }

    public void setDateTrajet(Date dateTrajet) {
        this.dateTrajet = dateTrajet;
    }

    public String getPointArrivee() {
        return pointArrivee;
    }

    public void setPointArrivee(String pointArrivee) {
        this.pointArrivee = pointArrivee;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public float getPrixTrajet() {
        return prixTrajet;
    }

    public void setPrixTrajet(float prixTrajet) {
        this.prixTrajet = prixTrajet;
    }

    public Integer getNb_Placedisponible() {
        return nb_Placedisponible;
    }

    public void setNb_Placedisponible(Integer nb_Placedisponible) {
        this.nb_Placedisponible = nb_Placedisponible;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        this.conducteur = conducteur;
    }


    public List<Passager> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<Passager> passagers) {
        this.passagers = passagers;
    }

}
