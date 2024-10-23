package com.example.hotel.controller;

import com.example.hotel.model.reservation.ReservationRequestDTO;
import com.example.hotel.model.reservation.ReservationResponseDTO;
import com.example.hotel.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping(value = "/reservation")
    public ResponseEntity<Integer> add(@RequestBody ReservationRequestDTO requestDTO) {
        Integer savedId = reservationService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/reservation/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ReservationRequestDTO requestDTO) {
        reservationService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/reservation/{id}")
    public ResponseEntity<ReservationResponseDTO> getById(@PathVariable Integer id) {
        ReservationResponseDTO responseDTO = reservationService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/reservation")
    public ResponseEntity<Page<ReservationResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<ReservationResponseDTO> page = reservationService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/reservation/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
