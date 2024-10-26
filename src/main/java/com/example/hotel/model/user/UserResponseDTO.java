package com.example.hotel.model.user;

import com.example.hotel.core.base.ResponseDTO;
import com.example.hotel.model.role.RoleResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class UserResponseDTO extends ResponseDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleResponseDTO> roles;
}
