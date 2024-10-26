package com.example.hotel.model.city;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.hotel.Hotel;
import com.example.hotel.model.state.Province;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "CITY")
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;

    @OneToMany(mappedBy = "city")
    private Set<Hotel> hotels;
}
