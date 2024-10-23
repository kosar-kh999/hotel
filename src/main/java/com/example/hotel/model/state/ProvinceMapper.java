package com.example.hotel.model.state;

import com.example.hotel.core.base.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapper implements BaseMapper<Province, ProvinceRequestDTO, ProvinceResponseDTO> {
    @Override
    public Province toEntity(ProvinceRequestDTO requestDTO) {
        Province province = new Province();
        toEntity(requestDTO, province);
        return province;
    }

    @Override
    public void toEntity(ProvinceRequestDTO requestDTO, Province province) {
        province.setName(requestDTO.getName());
    }

    @Override
    public ProvinceResponseDTO toDTO(Province province) {
        ProvinceResponseDTO responseDTO = ProvinceResponseDTO.builder().build();
        toDTO(province, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Province province, ProvinceResponseDTO responseDTO) {
        responseDTO.setName(province.getName());
        baseField(responseDTO, province);
    }
}
