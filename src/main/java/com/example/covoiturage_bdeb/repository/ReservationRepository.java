package com.example.covoiturage_bdeb.repository;

import com.example.covoiturage_bdeb.entity.Passager;
import com.example.covoiturage_bdeb.entity.Reservation;
import com.example.covoiturage_bdeb.entity.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    List<Reservation> findByIdreservationIdPassager(Passager passager);

    Optional<Reservation> findById(ReservationId id);
}

