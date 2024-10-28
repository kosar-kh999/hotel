package com.example.hotel.model.walletHistory;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.wallet.Wallet;
import com.example.hotel.model.walletHistory.enumuration.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "WALLET_HISTORY")
public class WalletHistory extends BaseEntity {
    private String description;
    private BigDecimal credit;
    private TransactionType transactionType;

    @CreatedDate
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;
}
