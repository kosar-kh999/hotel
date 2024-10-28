package com.example.hotel.model.user;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.creditTransfer.CreditTransfer;
import com.example.hotel.model.role.Role;
import com.example.hotel.model.wallet.Wallet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "`USER`", uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME"}, name = "user_username_uc"))
public class User extends BaseEntity {

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<CreditTransfer> creditTransfers;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Wallet wallet;
}
