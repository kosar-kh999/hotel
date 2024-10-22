package com.example.hotel.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
}
