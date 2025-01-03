package com.example.hotel.model.room;

import com.example.hotel.core.base.BaseMapper;
import com.example.hotel.model.city.CityMapper;
import com.example.hotel.model.hotel.HotelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper implements BaseMapper<Room, RoomRequestDTO, RoomResponseDTO> {
    private final HotelMapper hotelMapper;

    private final CityMapper cityMapper;

    public RoomMapper(HotelMapper hotelMapper,
                      CityMapper cityMapper) {
        this.hotelMapper = hotelMapper;
        this.cityMapper = cityMapper;
    }

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
        room.setAvailable(dto.getAvailable());
        room.setHotel(hotelMapper.toEntity(dto.getHotel()));
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
        dto.setAvailable(room.getAvailable());
        dto.setHotel(hotelMapper.toDTO(room.getHotel()));
        baseField(dto, room);
    }

    public RoomResponseDTO toDTOForCity(Room room) {
        RoomResponseDTO dto = RoomResponseDTO.builder().build();
        dto.setRoomType(room.getRoomType());
        dto.setPrice(room.getPrice());
        dto.setAvailable(room.getAvailable());
        dto.setHotel(hotelMapper.toDTO(room.getHotel()));
        dto.getHotel().setCity(cityMapper.toDTO(room.getHotel().getCity()));
        baseField(dto, room);
        return dto;
    }
}
