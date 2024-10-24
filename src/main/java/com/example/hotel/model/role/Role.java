package com.example.hotel.model.role;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {

    private String name;
    private String code;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
