package com.example.hotel.model.city;

import com.example.hotel.core.base.RequestDTO;
import com.example.hotel.model.state.ProvinceRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CityRequestDTO extends RequestDTO {
    private String name;
    private ProvinceRequestDTO province;
}
