package com.example.hotel.model.room;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.hotel.Hotel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ROOM")
public class Room extends BaseEntity {

    private String roomType;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
