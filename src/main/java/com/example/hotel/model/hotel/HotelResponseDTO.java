package com.example.hotel.model.hotel;

import com.example.hotel.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class HotelResponseDTO extends ResponseDTO {
    private String name;
    private String location;
}
