package com.example.covoiturage_bdeb.service;

import com.example.covoiturage_bdeb.entity.Trajet;
import com.example.covoiturage_bdeb.repository.TrajetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrajetService {
    private ConducteurService conducteurService;
    @Autowired
    private TrajetRepository trajetRepository ;

    public Trajet save(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    public List<Trajet> chercherUnTrajet(String pointDepart, String pointArrivee) {
        return trajetRepository.chercherUnTrajet(pointDepart, pointArrivee);
    }

    public List<Trajet> findAll() {
        return trajetRepository.findAll();
    }

    public List<Trajet> getTrajetsByConducteurId(Integer idConducteur) {
        return trajetRepository.findByConducteurIdConducteur(idConducteur);
    }

    public List<Trajet> chercherTrajetByHeureDepart(String heuredepart){
        return this.trajetRepository.findTrajetByHeureDepart(heuredepart);

    }
    //Update Trajet
    public Trajet getTrajetById(Integer idTrajet) {
        return trajetRepository.findById(idTrajet)
                .orElseThrow(() -> new RuntimeException("Trajet not found with id : " + idTrajet));
    }
    public Optional<Trajet> findById(Integer id) {
        return trajetRepository.findById(id);
    }
    public void update(Trajet trajetActuel) {
        trajetRepository.save(trajetActuel);
    }

    // Delete Trajet

    public void delete(Integer trajetId) throws ChangeSetPersister.NotFoundException {
        if (trajetRepository.existsById(trajetId)) {
            trajetRepository.deleteById(trajetId);
        } else {
            // identifiant n'existe pas
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}
