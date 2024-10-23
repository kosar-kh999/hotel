package com.example.hotel.controller;

import com.example.hotel.model.state.ProvinceRequestDTO;
import com.example.hotel.model.state.ProvinceResponseDTO;
import com.example.hotel.service.ProvinceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping(value = "/vprovince")
    public ResponseEntity<Integer> add(@RequestBody ProvinceRequestDTO requestDTO) {
        Integer savedId = provinceService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/province/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ProvinceRequestDTO requestDTO) {
        provinceService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/province/{id}")
    public ResponseEntity<ProvinceResponseDTO> getById(@PathVariable Integer id) {
        ProvinceResponseDTO responseDTO = provinceService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/province")
    public ResponseEntity<Page<ProvinceResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<ProvinceResponseDTO> page = provinceService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/province/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        provinceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
