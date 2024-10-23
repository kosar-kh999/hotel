package com.example.hotel.model.city;

import com.example.hotel.core.base.ResponseDTO;
import com.example.hotel.model.state.ProvinceResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class CityResponseDTO extends ResponseDTO {
    private String name;
    private ProvinceResponseDTO province;
}
