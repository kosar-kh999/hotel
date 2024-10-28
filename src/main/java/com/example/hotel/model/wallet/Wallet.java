package com.example.hotel.model.wallet;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "WALLET")
public class Wallet extends BaseEntity {

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
