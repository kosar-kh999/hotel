package com.example.hotel.model.room;

import com.example.hotel.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class RoomResponseDTO extends ResponseDTO {
    private String roomType;
    private double price;
}
