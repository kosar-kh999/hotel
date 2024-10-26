package com.example.hotel.model.room;

import com.example.hotel.core.base.RequestDTO;
import com.example.hotel.model.hotel.HotelRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO extends RequestDTO {
    private String roomType;
    private Double price;
    private Boolean available;
    private HotelRequestDTO hotel;
}
