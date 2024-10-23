package com.example.hotel.model.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Integer> {
    List<Room> findByHotelId(Integer hotelId);
}
