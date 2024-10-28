package com.example.hotel.controller;

import com.example.hotel.model.creditTransfer.CreditTransferRequestDTO;
import com.example.hotel.model.creditTransfer.CreditTransferResponseDTO;
import com.example.hotel.service.CreditTransferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreditTransferController {
    private final CreditTransferService creditTransferService;

    public CreditTransferController(CreditTransferService creditTransferService) {
        this.creditTransferService = creditTransferService;
    }

    @PostMapping(value = "/credit-transfer")
    public ResponseEntity<Integer> add(@RequestBody CreditTransferRequestDTO requestDTO) {
        Integer savedId = creditTransferService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/credit-transfer/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody CreditTransferRequestDTO requestDTO) {
        creditTransferService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/credit-transfer/{id}")
    public ResponseEntity<CreditTransferResponseDTO> getById(@PathVariable Integer id) {
        CreditTransferResponseDTO responseDTO = creditTransferService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/credit-transfer")
    public ResponseEntity<Page<CreditTransferResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<CreditTransferResponseDTO> page = creditTransferService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/credit-transfer/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        creditTransferService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
