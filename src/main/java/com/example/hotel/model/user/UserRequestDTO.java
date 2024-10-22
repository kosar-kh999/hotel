package com.example.hotel.model.user;

import com.example.hotel.core.base.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
}
