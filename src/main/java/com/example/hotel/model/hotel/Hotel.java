package com.example.hotel.model.hotel;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.city.City;
import com.example.hotel.model.room.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "HOTEL")
public class Hotel extends BaseEntity {

    private String name;
    private String location;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
