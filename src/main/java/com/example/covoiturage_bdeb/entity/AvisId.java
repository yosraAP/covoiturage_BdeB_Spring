package com.example.covoiturage_bdeb.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable  //Utiliser quand il ya plusieur cle permiere
public class AvisId implements Serializable {


    private Integer idPassager;


    private Integer idTrajet;

    public AvisId() {
    }

    public AvisId(Integer idPassager, Integer idTrajet) {
        this.idPassager = idPassager;
        this.idTrajet = idTrajet;
    }

    public Integer getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(Integer idPassager) {
        this.idPassager = idPassager;
    }

    public Integer getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Integer idTrajet) {
        this.idTrajet = idTrajet;
    }

    @Override
    public boolean equals(Object o) {
//        return super.equals(obj);

        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AvisId avisId = (AvisId) o;
        return idPassager.equals(avisId.idPassager) && idTrajet.equals(avisId.idTrajet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPassager, idTrajet);
    }
}
