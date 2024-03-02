package com.example.covoiturage_bdeb.repository;

import com.example.covoiturage_bdeb.entity.Avis;
import com.example.covoiturage_bdeb.entity.AvisId;
import com.example.covoiturage_bdeb.entity.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis,Integer> {

     Avis findAvisByIdAvis(AvisId avisId);
}
