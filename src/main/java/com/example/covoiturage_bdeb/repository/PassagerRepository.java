package com.example.covoiturage_bdeb.repository;

import com.example.covoiturage_bdeb.entity.Passager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PassagerRepository extends JpaRepository<Passager,Integer> {

    Passager findByEmailPassager(String email);

    Passager save(Passager passager);
    @Modifying
    @Query("update Trajet t set t.pointDepart = :pointDepart, t.pointArrivee = :pointArrivee where t.idTrajet = :idTrajet")
    void updateTrajetInfo(@Param("pointDepart") String pointDepart, @Param("pointArrivee") String pointArrivee, @Param("idTrajet") Integer idTrajet);
}
