package com.example.hotel.model.state;

import com.example.hotel.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ProvinceResponseDTO extends ResponseDTO {
    private String name;
}
