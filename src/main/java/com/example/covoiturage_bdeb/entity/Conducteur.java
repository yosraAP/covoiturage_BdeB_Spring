package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conducteur")
public class Conducteur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idConducteur")
    private Integer idConducteur;
    private String nomConducteur;
    private String prenomConducteur;
    private String adresseConducteur;
    private String numtelephoneConducteur;
    private String emailConducteur;
    private String passwordConducteur;
    private String typeCompte;
    private String modelVoiture;
    private Integer nbPlaces;
    private String ImageVoiture;
    private String Immatriculation;

    @OneToMany(mappedBy="idConducteur",cascade = CascadeType.ALL,orphanRemoval = true) //conducteur
    private List<Messagerie> messageries= new ArrayList<>();
    @OneToMany(mappedBy="conducteur",cascade = CascadeType.ALL)
    private List<Trajet> trajets= new ArrayList<>();

    public Conducteur() {
    }

    public Conducteur(String nomConducteur, String prenomConducteur, String adresseConducteur, String numtelephoneConducteur, String emailConducteur, String passwordConducteur, String typeCompte, String modelVoiture, Integer nbPlaces, String imageVoiture, String immatriculation) {
        this.nomConducteur = nomConducteur;
        this.prenomConducteur = prenomConducteur;
        this.adresseConducteur = adresseConducteur;
        this.numtelephoneConducteur = numtelephoneConducteur;
        this.emailConducteur = emailConducteur;
        this.passwordConducteur = passwordConducteur;
        this.typeCompte = typeCompte;
        this.modelVoiture = modelVoiture;
        this.nbPlaces = nbPlaces;
        ImageVoiture = imageVoiture;
        Immatriculation = immatriculation;
    }

    public Conducteur(Integer idConducteur, String nomConducteur, String prenomConducteur, String adresseConducteur, String numtelephoneConducteur, String emailConducteur, String passwordConducteur, String typeCompte, String modelVoiture, Integer nbPlaces, String imageVoiture, String immatriculation, List<Messagerie> messageries, List<Trajet> trajets) {
        this.idConducteur = idConducteur;
        this.nomConducteur = nomConducteur;
        this.prenomConducteur = prenomConducteur;
        this.adresseConducteur = adresseConducteur;
        this.numtelephoneConducteur = numtelephoneConducteur;
        this.emailConducteur = emailConducteur;
        this.passwordConducteur = passwordConducteur;
        this.typeCompte = typeCompte;
        this.modelVoiture = modelVoiture;
        this.nbPlaces = nbPlaces;
        ImageVoiture = imageVoiture;
        Immatriculation = immatriculation;
        this.messageries = messageries;
        this.trajets = trajets;
    }

    public Integer getIdConducteur() {
        return idConducteur;
    }

    public void setIdConducteur(Integer idConducteur) {
        this.idConducteur = idConducteur;
    }

    public String getNomConducteur() {
        return nomConducteur;
    }

    public void setNomConducteur(String nomConducteur) {
        this.nomConducteur = nomConducteur;
    }

    public String getPrenomConducteur() {
        return prenomConducteur;
    }

    public void setPrenomConducteur(String prenomConducteur) {
        this.prenomConducteur = prenomConducteur;
    }

    public String getAdresseConducteur() {
        return adresseConducteur;
    }

    public void setAdresseConducteur(String adresseConducteur) {
        this.adresseConducteur = adresseConducteur;
    }

    public String getNumtelephoneConducteur() {
        return numtelephoneConducteur;
    }

    public void setNumtelephoneConducteur(String numtelephoneConducteur) {
        this.numtelephoneConducteur = numtelephoneConducteur;
    }

    public String getEmailConducteur() {
        return emailConducteur;
    }

    public void setEmailConducteur(String emailConducteur) {
        this.emailConducteur = emailConducteur;
    }

    public String getPasswordConducteur() {
        return passwordConducteur;
    }

    public void setPasswordConducteur(String passwordConducteur) {
        this.passwordConducteur = passwordConducteur;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getModelVoiture() {
        return modelVoiture;
    }

    public void setModelVoiture(String modelVoiture) {
        this.modelVoiture = modelVoiture;
    }

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getImageVoiture() {
        return ImageVoiture;
    }

    public void setImageVoiture(String imageVoiture) {
        ImageVoiture = imageVoiture;
    }

    public String getImmatriculation() {
        return Immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        Immatriculation = immatriculation;
    }

    public List<Messagerie> getMessageries() {
        return messageries;
    }

    public void setMessageries(List<Messagerie> messageries) {
        this.messageries = messageries;
    }

    public List<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(List<Trajet> trajets) {
        this.trajets = trajets;
    }
}
