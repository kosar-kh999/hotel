package com.example.hotel.model.creditTransfer.record;

import java.math.BigDecimal;

public record CreditTransferRecord(Integer userId,
                                   BigDecimal amount,
                                   String description) {
}
