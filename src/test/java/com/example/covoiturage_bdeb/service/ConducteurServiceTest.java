package com.example.covoiturage_bdeb.service;

import com.example.covoiturage_bdeb.entity.Conducteur;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.repository.ConducteurRepository;
import com.example.covoiturage_bdeb.repository.PassagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConducteurServiceTest {
    @Autowired
    private ConducteurService conducteurService;

    @Mock
    private ConducteurRepository conducteurRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        conducteurService = new ConducteurService(conducteurRepository);
    }
    @Test
    void inscrireConducteur() {

        Conducteur conducteur = new Conducteur();
        conducteur.setNomConducteur("Houas");
        conducteur.setPrenomConducteur("Yosra");
        conducteur.setAdresseConducteur("5185 Desmarteau");
        conducteur.setEmailConducteur("yosra@site.com");
        conducteur.setNumtelephoneConducteur("5145492221");
        conducteur.setPasswordConducteur("yosra");
        conducteur.setTypeCompte("conducteur");
        conducteur.setImmatriculation("HOU150891");
        conducteur.setImageVoiture("test.png");

        when(conducteurRepository.save(conducteur)).thenReturn(conducteur);
        Conducteur savedPassager = conducteurService.inscrireConducteur(conducteur);
        assertEquals(conducteur, savedPassager);
    }

    @Test
    void findByEmailConducteur() {
        String testEmail = "yosra@site.com";

        Conducteur conducteur = new Conducteur();
        conducteur.setIdConducteur(1);
        conducteur.setNomConducteur("Houas");
        conducteur.setPrenomConducteur("Yosra");
        conducteur.setEmailConducteur(testEmail);
        conducteur.setPasswordConducteur("yosra");
        conducteur.setTypeCompte("conducteur");

        Mockito.when(conducteurRepository.findByEmailConducteur(testEmail)).thenReturn(conducteur);

        Conducteur foundConducteur = conducteurService.findByEmailConducteur(testEmail);

        assertNotNull(foundConducteur);
        assertEquals(conducteur.getIdConducteur(), foundConducteur.getIdConducteur());
        assertEquals(conducteur.getNomConducteur(), foundConducteur.getNomConducteur());
        assertEquals(conducteur.getPrenomConducteur(), foundConducteur.getPrenomConducteur());
        assertEquals(conducteur.getEmailConducteur(), foundConducteur.getEmailConducteur());
        assertEquals(conducteur.getPasswordConducteur(),foundConducteur.getPasswordConducteur());
    }
}