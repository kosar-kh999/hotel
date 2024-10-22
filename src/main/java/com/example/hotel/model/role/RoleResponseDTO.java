package com.example.hotel.model.role;

import com.example.hotel.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class RoleResponseDTO extends ResponseDTO {
    private String name;
    private String code;
}
