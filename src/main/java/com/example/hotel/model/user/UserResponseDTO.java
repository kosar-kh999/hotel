package com.example.hotel.model.user;

import com.example.hotel.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class UserResponseDTO extends ResponseDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
