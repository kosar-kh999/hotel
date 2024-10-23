package com.example.hotel.model.user;

import com.example.hotel.core.base.RequestDTO;
import com.example.hotel.model.role.RoleRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO extends RequestDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private RoleRequestDTO role;
    private Set<RoleRequestDTO> roles;
}
