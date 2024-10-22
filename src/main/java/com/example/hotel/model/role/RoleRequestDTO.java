package com.example.hotel.model.role;

import com.example.hotel.core.base.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO extends RequestDTO {
    private String name;
    private String code;
}
