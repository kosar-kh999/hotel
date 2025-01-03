package com.example.hotel.model.reservation;

import com.example.hotel.core.base.ResponseDTO;
import com.example.hotel.model.room.RoomResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@SuperBuilder
public class ReservationResponseDTO extends ResponseDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomResponseDTO room;
}
