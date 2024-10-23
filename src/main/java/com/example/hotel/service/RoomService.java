package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.hotel.Hotel;
import com.example.hotel.model.hotel.HotelRepo;
import com.example.hotel.model.room.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepo roomRepo,
                       HotelRepo hotelRepo,
                       RoomMapper roomMapper) {
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
        this.roomMapper = roomMapper;
    }


    public Integer save(RoomRequestDTO requestDTO) {
        Room room = roomMapper.toEntity(requestDTO);
        return roomRepo.save(room).getId();
    }

    public void update(Integer id, RoomRequestDTO requestDTO) {
        Optional<Room> roomOpt = roomRepo.findById(id);
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roomMapper.toEntity(requestDTO, room);
        roomRepo.save(room);
    }

    public RoomResponseDTO findById(Integer id) {
        Optional<Room> roomOpt = roomRepo.findById(id);
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return roomMapper.toDTO(room);
    }

    public Page<RoomResponseDTO> findAll(Pageable pageable) {
        Page<Room> all = roomRepo.findAll(pageable);
        return roomMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<Room> roomOpt = roomRepo.findById(id);
        Room room = roomOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roomRepo.delete(room);
    }

    public List<RoomResponseDTO> getRoomsByHotel(Integer id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return roomRepo.findAll().stream()
                .filter(room -> room.getHotel().getId().equals(hotel.getId()))
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RoomResponseDTO> getRoomsByHotelAndCity(Integer id) {
        List<Hotel> hotels = hotelRepo.findByCityId(id);
        return hotels.stream()
                .flatMap(hotel -> roomRepo.findByHotelId(hotel.getId()).stream())
                .map(roomMapper::toDTO)
                .toList();
    }
}
