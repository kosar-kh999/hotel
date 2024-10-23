package com.example.hotel.model.reservation;

import com.example.hotel.core.base.RequestDTO;
import com.example.hotel.model.room.RoomRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO extends RequestDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private RoomRequestDTO room;
}
