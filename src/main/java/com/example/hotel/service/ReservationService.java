package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.reservation.record.ReservationRecord;
import com.example.hotel.core.util.DateUtil;
import com.example.hotel.model.reservation.*;
import com.example.hotel.model.room.Room;
import com.example.hotel.model.room.RoomRepo;
import com.example.hotel.model.user.User;
import com.example.hotel.model.user.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final RoomRepo roomRepo;

    private final UserRepo userRepo;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepo reservationRepo,
                              RoomRepo roomRepo,
                              UserRepo userRepo,
                              ReservationMapper reservationMapper) {
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
        this.reservationMapper = reservationMapper;
    }

    public Integer save(ReservationRequestDTO requestDTO) {
        Reservation reservation = reservationMapper.toEntity(requestDTO);
        Optional<Room> roomOpt = roomRepo.findById(requestDTO.getRoom().getId());
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.getRoom().getId())));
        reservation.setRoom(room);
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

    @Transactional
    public ReservationDTO reserveRoom(ReservationRecord reservationRecord) {
        Room room = roomRepo.findById(reservationRecord.roomId())
                .orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", reservationRecord.roomId())));

        if (!room.getAvailable())
            throw new CustomException("اتاق برای رزواریسیون در دسترس نیست.");

        if (!isRoomAvailableForDates(reservationRecord))
            throw new CustomException("برای این بازه نمیتوان رزور کرد.");

        room.setAvailable(false);
        roomRepo.save(room);

        User user = userRepo.findById(reservationRecord.userId())
                .orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", reservationRecord.roomId())));

        LocalDate checkInDate = DateUtil.convertPersianDateStringToLocalDate(reservationRecord.persianCheckInDate());
        LocalDate checkOutDate = DateUtil.convertPersianDateStringToLocalDate(reservationRecord.persianCheckOutDate());
        Period period = Period.between(checkInDate, checkOutDate);
        Integer days = period.getDays();
        BigDecimal totalValue = BigDecimal.valueOf(days * room.getPrice());

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setTotalPrice(totalValue);
        reservationDTO.setPersianCheckInDate(reservationRecord.persianCheckInDate());
        reservationDTO.setPersianCheckOutDate(reservationRecord.persianCheckOutDate());

        reservationRepo.save(reservation);
        return reservationMapper.toDTO(reservation, reservationDTO);
    }

    private boolean isRoomAvailableForDates(ReservationRecord reservationRecord) {
        List<Reservation> existingReservations = reservationRepo.findAll();

        LocalDate checkInDate = DateUtil.convertPersianDateStringToLocalDate(reservationRecord.persianCheckInDate());
        LocalDate checkOutDate = DateUtil.convertPersianDateStringToLocalDate(reservationRecord.persianCheckOutDate());

        if (checkInDate.isBefore(LocalDate.now()) || checkOutDate.isBefore(LocalDate.now())) {
            return false;
        }

        for (Reservation reservation : existingReservations) {
            if (reservation.getRoom().getId().equals(reservationRecord.roomId())) {
                if ((checkInDate.isBefore(reservation.getCheckOutDate()) && checkOutDate.isAfter(reservation.getCheckInDate()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
