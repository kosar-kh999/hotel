package com.example.hotel.model.state;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.city.City;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "PROVINCE")
public class Province extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "province")
    private Set<City> city;
}
