package com.example.hotel.model.room;

import com.example.hotel.core.base.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper implements BaseMapper<Room, RoomRequestDTO, RoomResponseDTO> {
    @Override
    public Room toEntity(RoomRequestDTO dto) {
        Room room = new Room();
        toEntity(dto, room);
        return room;
    }

    @Override
    public void toEntity(RoomRequestDTO dto, Room room) {
        room.setRoomType(dto.getRoomType());
        room.setPrice(dto.getPrice());
    }

    @Override
    public RoomResponseDTO toDTO(Room room) {
        RoomResponseDTO responseDTO = RoomResponseDTO.builder().build();
        toDTO(room, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Room room, RoomResponseDTO dto) {
        dto.setRoomType(room.getRoomType());
        dto.setPrice(room.getPrice());
        baseField(dto, room);
    }
}
