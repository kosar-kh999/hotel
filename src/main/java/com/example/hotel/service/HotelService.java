package com.example.hotel.service;

import com.example.hotel.model.hotel.Hotel;
import com.example.hotel.model.hotel.HotelMapper;
import com.example.hotel.model.hotel.HotelRepo;
import com.example.hotel.model.hotel.HotelRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private final HotelRepo hotelRepo;
    private final HotelMapper hotelMapper;

    public HotelService(HotelRepo hotelRepo,
                        HotelMapper hotelMapper) {
        this.hotelRepo = hotelRepo;
        this.hotelMapper = hotelMapper;
    }

    public Integer save(HotelRequestDTO requestDTO) {
        Hotel hotel = hotelMapper.toEntity(requestDTO);
        return hotelRepo.save(hotel).getId();
    }
}
