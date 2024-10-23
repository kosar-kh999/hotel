package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.room.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepo roomRepo;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepo roomRepo,
                       RoomMapper roomMapper) {
        this.roomRepo = roomRepo;
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
}
