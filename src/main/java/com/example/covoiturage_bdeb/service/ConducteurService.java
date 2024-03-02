package com.example.covoiturage_bdeb.service;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConducteurService {
    private final ConducteurRepository conducteurRepository;
    @Autowired
    public ConducteurService(ConducteurRepository conducteurRepository) {
        this.conducteurRepository = conducteurRepository;
    }

    public Conducteur inscrireConducteur(Conducteur conducteur) {
        return conducteurRepository.save(conducteur);
    }

    public Conducteur findByEmailConducteur(String email) {
        return conducteurRepository.findByEmailConducteur(email);
    }

}
