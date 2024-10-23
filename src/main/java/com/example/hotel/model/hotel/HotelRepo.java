package com.example.hotel.model.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h WHERE h.city.id = :cityId")
    List<Hotel> findByCityId(@Param("cityId") Integer cityId);
}
