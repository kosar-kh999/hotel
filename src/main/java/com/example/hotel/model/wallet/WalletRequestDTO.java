package com.example.hotel.model.wallet;

import com.example.hotel.core.base.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequestDTO extends RequestDTO {
    private BigDecimal balance;
}
