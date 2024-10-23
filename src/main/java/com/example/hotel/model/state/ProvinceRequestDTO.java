package com.example.hotel.model.state;

import com.example.hotel.core.base.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceRequestDTO extends RequestDTO {
    private String name;
}
