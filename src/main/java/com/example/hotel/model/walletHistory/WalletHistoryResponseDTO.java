package com.example.hotel.model.walletHistory;

import com.example.hotel.core.base.ResponseDTO;
import com.example.hotel.model.walletHistory.enumuration.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
public class WalletHistoryResponseDTO extends ResponseDTO {
    private String description;
    private BigDecimal credit;
    private TransactionType transactionType;
}
