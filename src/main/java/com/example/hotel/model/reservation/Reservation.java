package com.example.hotel.model.reservation;

import com.example.hotel.core.base.BaseEntity;
import com.example.hotel.model.room.Room;
import com.example.hotel.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "RESERVATION")
public class Reservation extends BaseEntity {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
