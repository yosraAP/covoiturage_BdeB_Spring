package com.example.covoiturage_bdeb.service;

import com.example.covoiturage_bdeb.entity.Avis;
import com.example.covoiturage_bdeb.entity.AvisId;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.entity.Trajet;
import com.example.covoiturage_bdeb.repository.AvisRepository;
import com.example.covoiturage_bdeb.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AvisService {

    @Autowired
    private AvisRepository avisRepository ;

    public Avis save(Avis avis) { return avisRepository.save(avis); }

    public Avis findAvisByIdAvis(AvisId avisId) {
        return avisRepository.findAvisByIdAvis(avisId);
    }

}
