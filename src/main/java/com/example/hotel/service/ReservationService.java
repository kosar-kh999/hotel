package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.reservation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepo reservationRepo,
                              ReservationMapper reservationMapper) {
        this.reservationRepo = reservationRepo;
        this.reservationMapper = reservationMapper;
    }

    public Integer save(ReservationRequestDTO requestDTO) {
        Reservation reservation = reservationMapper.toEntity(requestDTO);
        return reservationRepo.save(reservation).getId();
    }

    public void update(Integer id, ReservationRequestDTO requestDTO) {
        Optional<Reservation> reservationOpt = reservationRepo.findById(id);
        Reservation reservation = reservationOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        reservationMapper.toEntity(requestDTO, reservation);
        reservationRepo.save(reservation);
    }

    public ReservationResponseDTO findById(Integer id) {
        Optional<Reservation> reservationOpt = reservationRepo.findById(id);
        Reservation reservation = reservationOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return reservationMapper.toDTO(reservation);
    }

    public Page<ReservationResponseDTO> findAll(Pageable pageable) {
        Page<Reservation> all = reservationRepo.findAll(pageable);
        return reservationMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<Reservation> reservationOpt = reservationRepo.findById(id);
        Reservation reservation = reservationOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        reservationRepo.delete(reservation);
    }
}
