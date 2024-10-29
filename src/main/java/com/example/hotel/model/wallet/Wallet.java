package com.example.hotel.model.wallet;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.user.User;
import com.example.hotel.model.walletHistory.WalletHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "WALLET")
public class Wallet extends BaseEntity {

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
    private List<WalletHistory> walletHistories;
}
