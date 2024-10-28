package com.example.hotel.model.creditTransfer;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.creditTransfer.enumuration.CreditTransferType;
import com.example.hotel.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "CREDIT_TRANSFER")
public class CreditTransfer extends BaseEntity {
    private BigDecimal amount;
    private String description;
    private CreditTransferType creditTransferType;

    @OneToOne
    private User user;
}
