package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "avis")

public class Avis {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="idAvis")
//    private Integer idAvis;

@EmbeddedId
private AvisId idAvis;

    private Integer nb_Etoiles;
    private String Commentaire;

    public Avis() {
    }

    public Avis(AvisId idAvis, Integer nb_Etoiles, String commentaire) {
        this.idAvis = idAvis;
        this.nb_Etoiles = nb_Etoiles;
        Commentaire = commentaire;
    }

    public AvisId getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(AvisId idAvis) {
        this.idAvis = idAvis;
    }

    public Integer getNb_Etoiles() {
        return nb_Etoiles;
    }

    public void setNb_Etoiles(Integer nb_Etoiles) {
        this.nb_Etoiles = nb_Etoiles;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String commentaire) {
        Commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "idAvis=" + idAvis +
                ", nb_Etoiles=" + nb_Etoiles +
                ", Commentaire='" + Commentaire + '\'' +
                '}';
    }
}
