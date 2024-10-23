package com.example.hotel.model.reservation;

import com.example.hotel.core.base.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper implements BaseMapper<Reservation, ReservationRequestDTO, ReservationResponseDTO> {
    @Override
    public Reservation toEntity(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();
        toEntity(dto, reservation);
        return reservation;
    }

    @Override
    public void toEntity(ReservationRequestDTO dto, Reservation reservation) {
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setAvailable(dto.getAvailable());
    }

    @Override
    public ReservationResponseDTO toDTO(Reservation reservation) {
        ReservationResponseDTO responseDTO = ReservationResponseDTO.builder().build();
        toDTO(reservation, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Reservation reservation, ReservationResponseDTO dto) {
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setAvailable(reservation.getAvailable());
        baseField(dto, reservation);
    }
}
