package com.example.hotel.controller;

import com.example.hotel.model.city.CityRequestDTO;
import com.example.hotel.model.city.CityResponseDTO;
import com.example.hotel.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/city")
    public ResponseEntity<Integer> add(@RequestBody CityRequestDTO requestDTO) {
        Integer savedId = cityService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/city/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody CityRequestDTO requestDTO) {
        cityService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/city/{id}")
    public ResponseEntity<CityResponseDTO> getById(@PathVariable Integer id) {
        CityResponseDTO responseDTO = cityService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/city")
    public ResponseEntity<Page<CityResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<CityResponseDTO> page = cityService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/v1/city/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
