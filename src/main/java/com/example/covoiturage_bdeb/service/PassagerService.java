package com.example.covoiturage_bdeb.service;

import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.repository.PassagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassagerService {
    private PassagerRepository passagerRepository;

    public PassagerService(PassagerRepository passagerRepository) {
        this.passagerRepository = passagerRepository;
    }


    public Passager save(Passager passager) {
        return passagerRepository.save(passager);
    }

    public Passager findByEmailPassager(String email) {
        return passagerRepository.findByEmailPassager(email);
    }


}
