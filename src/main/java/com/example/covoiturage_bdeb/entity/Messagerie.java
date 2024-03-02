package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "messagerie")
public class Messagerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMessagerie")
    private Integer idMessagerie;
    private String destinataire;
    private String objet;
    private String contenu;


    @ManyToOne  //   @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idConducteur_FK",nullable = false)
    private Conducteur idConducteur;

    @ManyToOne
    @JoinColumn(name = "idPassager_FK",nullable = false)
     private Passager idPassager;

    public Messagerie() {
    }

    public Messagerie(String destinataire, String objet, String contenu, Conducteur idConducteur, Passager idPassager) {
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenu = contenu;
        this.idConducteur = idConducteur;
        this.idPassager = idPassager;
    }

    public Messagerie(Integer idMessagerie, String destinataire, String objet, String contenu, Conducteur idConducteur, Passager idPassager) {
        this.idMessagerie = idMessagerie;
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenu = contenu;
        this.idConducteur = idConducteur;
        this.idPassager = idPassager;
    }

    public Integer getIdMessagerie() {
        return idMessagerie;
    }

    public void setIdMessagerie(Integer idMessagerie) {
        this.idMessagerie = idMessagerie;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Conducteur getIdConducteur() {
        return idConducteur;
    }

    public void setIdConducteur(Conducteur idConducteur) {
        this.idConducteur = idConducteur;
    }

    public Passager getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(Passager idPassager) {
        this.idPassager = idPassager;
    }

    @Override
    public String toString() {
        return "Messagerie{" +
                "idMessagerie=" + idMessagerie +
                ", destinataire='" + destinataire + '\'' +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                ", idConducteur=" + idConducteur +
                ", idPassager=" + idPassager +
                '}';
    }
}
