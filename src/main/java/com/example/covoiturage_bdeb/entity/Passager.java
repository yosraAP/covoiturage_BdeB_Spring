package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passager")
public class Passager{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPassager")
    private Integer idPassager;
    private String nomPassager;
    private String prenomPassager;
    private String adressePassager;
    private String numtelephonePassager;
    private String emailPassager;
    private String passwordPassager;
    private String typeCompte;


    @OneToMany(mappedBy="idPassager",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Messagerie> messageries= new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idTrajet_FK")
    private Trajet Trajet;

    public Passager() {
    }

    public Passager(String nomPassager, String prenomPassager, String adressePassager, String numtelephonePassager, String emailPassager, String passwordPassager, String typeCompte) {
        this.nomPassager = nomPassager;
        this.prenomPassager = prenomPassager;
        this.adressePassager = adressePassager;
        this.numtelephonePassager = numtelephonePassager;
        this.emailPassager = emailPassager;
        this.passwordPassager = passwordPassager;
        this.typeCompte = typeCompte;
    }

    public Passager(Integer idPassager, String nomPassager, String prenomPassager, String adressePassager, String numtelephonePassager, String emailPassager, String passwordPassager, String typeCompte) {
        this.idPassager = idPassager;
        this.nomPassager = nomPassager;
        this.prenomPassager = prenomPassager;
        this.adressePassager = adressePassager;
        this.numtelephonePassager = numtelephonePassager;
        this.emailPassager = emailPassager;
        this.passwordPassager = passwordPassager;
        this.typeCompte = typeCompte;
    }

    public Integer getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(Integer idPassager) {
        this.idPassager = idPassager;
    }

    public String getNomPassager() {
        return nomPassager;
    }

    public void setNomPassager(String nomPassager) {
        this.nomPassager = nomPassager;
    }

    public String getPrenomPassager() {
        return prenomPassager;
    }

    public void setPrenomPassager(String prenomPassager) {
        this.prenomPassager = prenomPassager;
    }

    public String getAdressePassager() {
        return adressePassager;
    }

    public void setAdressePassager(String adressePassager) {
        this.adressePassager = adressePassager;
    }

    public String getNumtelephonePassager() {
        return numtelephonePassager;
    }

    public void setNumtelephonePassager(String numtelephonePassager) {
        this.numtelephonePassager = numtelephonePassager;
    }

    public String getEmailPassager() {
        return emailPassager;
    }

    public void setEmailPassager(String emailPassager) {
        this.emailPassager = emailPassager;
    }

    public String getPasswordPassager() {
        return passwordPassager;
    }

    public void setPasswordPassager(String passwordPassager) {
        this.passwordPassager = passwordPassager;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public List<Messagerie> getMessageries() {
        return messageries;
    }

    public void setMessageries(List<Messagerie> messageries) {
        this.messageries = messageries;
    }

    public com.example.covoiturage_bdeb.entity.Trajet getTrajet() {
        return Trajet;
    }

    public void setTrajet(com.example.covoiturage_bdeb.entity.Trajet trajet) {
        Trajet = trajet;
    }
}
