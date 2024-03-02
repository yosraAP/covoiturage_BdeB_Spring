package com.example.covoiturage_bdeb.repository;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Passager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConducteurRepository extends JpaRepository<Conducteur,Integer> {
    Conducteur findByEmailConducteur(String email);

    Conducteur save(Conducteur conducteur);


}
