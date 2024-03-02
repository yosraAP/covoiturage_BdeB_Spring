package com.example.covoiturage_bdeb.service;
import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.repository.PassagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class PassagerServiceTest {
    @Autowired
    private PassagerService passagerService;

    @Mock
    private PassagerRepository passagerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        passagerService = new PassagerService(passagerRepository);
    }
    @Test
    void testAjouterNouveauPassager() {
        Passager passager = new Passager();
        passager.setNomPassager("Houas");
        passager.setPrenomPassager("Yosra");
        passager.setAdressePassager("5185 Desmarteau");
        passager.setEmailPassager("yosra@site.com");
        passager.setNumtelephonePassager("5145492221");
        passager.setPasswordPassager("yosra");
        passager.setTypeCompte("passager");

        when(passagerRepository.save(passager)).thenReturn(passager);
        Passager savedPassager = passagerService.save(passager);
        assertEquals(passager, savedPassager);
    }

    @Test
    void findByEmailPassager() {
        String testEmail = "yosra@site.com";

        Passager passager = new Passager();
        passager.setIdPassager(1);
        passager.setNomPassager("Houas");
        passager.setPrenomPassager("Yosra");
        passager.setEmailPassager(testEmail);
        passager.setPasswordPassager("yosra");
        passager.setPasswordPassager("passager");

        Mockito.when(passagerRepository.findByEmailPassager(testEmail)).thenReturn(passager);

        Passager foundPassager = passagerService.findByEmailPassager(testEmail);

        assertNotNull(foundPassager);
        assertEquals(passager.getIdPassager(), foundPassager.getIdPassager());
        assertEquals(passager.getNomPassager(), foundPassager.getNomPassager());
        assertEquals(passager.getPrenomPassager(), foundPassager.getPrenomPassager());
        assertEquals(passager.getEmailPassager(), foundPassager.getEmailPassager());
        assertEquals(passager.getPasswordPassager(),foundPassager.getPasswordPassager());
    }
}