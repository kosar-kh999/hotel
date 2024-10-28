package com.example.hotel.controller;

import com.example.hotel.model.wallet.WalletRequestDTO;
import com.example.hotel.model.wallet.WalletResponseDTO;
import com.example.hotel.service.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping(value = "/wallet")
    public ResponseEntity<Integer> add(@RequestBody WalletRequestDTO requestDTO) {
        Integer savedId = walletService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/wallet/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody WalletRequestDTO requestDTO) {
        walletService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/wallet/{id}")
    public ResponseEntity<WalletResponseDTO> getById(@PathVariable Integer id) {
        WalletResponseDTO responseDTO = walletService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/wallet")
    public ResponseEntity<Page<WalletResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<WalletResponseDTO> page = walletService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/wallet/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
