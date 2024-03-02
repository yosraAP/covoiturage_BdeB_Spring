package com.example.covoiturage_bdeb.repository;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TrajetRepository extends JpaRepository<Trajet,Integer> {

    Trajet save(Trajet trajet);

    @Query("SELECT t FROM Trajet t WHERE t.pointDepart = :pointDepart AND t.pointArrivee = :pointArrivee")
    List<Trajet> chercherUnTrajet(@Param("pointDepart") String pointDepart, @Param("pointArrivee") String pointArrivee);

    @Modifying
    @Query("update Trajet t set t.dateTrajet = :dateTrajet, t.nb_Placedisponible = :nb_Placedisponible, t.prixTrajet = :prixTrajet where t.idTrajet = :idTrajet")
    void updateTrajetInfo(@Param("dateTrajet") LocalDate dateTrajet, @Param("nb_Placedisponible") Integer nb_Placedisponible, @Param("prixTrajet") BigDecimal prixTrajet, @Param("idTrajet") Integer idTrajet);

    List<Trajet> findByConducteurIdConducteur(Integer idConducteur);

    List<Trajet> findTrajetByHeureDepart(String HeureDepart);


}
