package com.example.hotel.model.reservation;

import com.example.hotel.model.room.RoomResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class ReservationDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String persianCheckInDate;
    private String persianCheckOutDate;
    private BigDecimal totalPrice;
    private RoomResponseDTO room;
}
