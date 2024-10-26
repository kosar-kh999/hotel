package com.example.hotel.controller;

import com.example.hotel.core.record.HotelRecord;
import com.example.hotel.model.hotel.HotelRequestDTO;
import com.example.hotel.model.hotel.HotelResponseDTO;
import com.example.hotel.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value = "/hotel")
    public ResponseEntity<Integer> add(@RequestBody HotelRecord requestDTO) {
        Integer savedId = hotelService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/hotel/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody HotelRequestDTO requestDTO) {
        hotelService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/hotel/{id}")
    public ResponseEntity<HotelResponseDTO> getById(@PathVariable Integer id) {
        HotelResponseDTO responseDTO = hotelService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/hotel")
    public ResponseEntity<Page<HotelResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<HotelResponseDTO> page = hotelService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/hotel/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
