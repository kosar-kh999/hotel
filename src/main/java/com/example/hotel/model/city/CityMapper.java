package com.example.hotel.model.city;

import com.example.hotel.core.base.BaseMapper;
import com.example.hotel.model.state.ProvinceMapper;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements BaseMapper<City, CityRequestDTO, CityResponseDTO> {
    private final ProvinceMapper provinceMapper;

    public CityMapper(ProvinceMapper provinceMapper) {
        this.provinceMapper = provinceMapper;
    }

    @Override
    public City toEntity(CityRequestDTO requestDTO) {
        City city = new City();
        toEntity(requestDTO, city);
        return city;
    }

    @Override
    public void toEntity(CityRequestDTO requestDTO, City city) {
        city.setName(requestDTO.getName());
        city.setProvince(provinceMapper.toEntity(requestDTO.getProvince()));
    }

    @Override
    public CityResponseDTO toDTO(City city) {
        CityResponseDTO responseDTO = CityResponseDTO.builder().build();
        toDTO(city, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(City city, CityResponseDTO responseDTO) {
        responseDTO.setName(city.getName());
        responseDTO.setProvince(provinceMapper.toDTO(city.getProvince()));
        baseField(responseDTO, city);
    }
}
