package com.example.hotel.core.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class ResponseDTO {
    private Integer id;
}
