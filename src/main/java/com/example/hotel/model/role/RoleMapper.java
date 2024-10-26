package com.example.hotel.model.role;

import com.example.hotel.core.base.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper implements BaseMapper<Role, RoleRequestDTO, RoleResponseDTO> {
    @Override
    public Role toEntity(RoleRequestDTO dto) {
        Role role = new Role();
        toEntity(dto, role);
        return role;
    }

    @Override
    public void toEntity(RoleRequestDTO dto, Role role) {
        role.setName(dto.getName());
        role.setCode(dto.getCode());
    }

    @Override
    public RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO responseDTO = RoleResponseDTO.builder().build();
        toDTO(role, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Role role, RoleResponseDTO dto) {
        dto.setName(role.getName());
        dto.setCode(role.getCode());
        baseField(dto, role);
    }
}
