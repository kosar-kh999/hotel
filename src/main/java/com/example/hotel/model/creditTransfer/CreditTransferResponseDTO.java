package com.example.hotel.model.creditTransfer;

import com.example.hotel.core.base.ResponseDTO;
import com.example.hotel.model.creditTransfer.enumuration.CreditTransferType;
import com.example.hotel.model.user.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class CreditTransferResponseDTO extends ResponseDTO {
    private BigDecimal amount;
    private String description;
    private CreditTransferType creditTransferType;
    private UserResponseDTO user;
}
