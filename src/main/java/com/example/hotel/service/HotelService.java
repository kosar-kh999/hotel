package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.hotel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void update(Integer id, HotelRequestDTO requestDTO) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        hotelMapper.toEntity(requestDTO, hotel);
        hotelRepo.save(hotel);
    }

    public HotelResponseDTO findById(Integer id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return hotelMapper.toDTO(hotel);
    }

    public Page<HotelResponseDTO> findAll(Pageable pageable) {
        Page<Hotel> all = hotelRepo.findAll(pageable);
        return hotelMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        Hotel hotel = hotelOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        hotelRepo.delete(hotel);
    }
}
