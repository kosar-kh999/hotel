package com.example.hotel.model.wallet;

import com.example.hotel.core.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class WalletResponseDTO extends ResponseDTO {
    private BigDecimal balance;
}
