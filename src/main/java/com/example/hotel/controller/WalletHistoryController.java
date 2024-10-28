package com.example.hotel.controller;

import com.example.hotel.model.walletHistory.WalletHistoryRequestDTO;
import com.example.hotel.model.walletHistory.WalletHistoryResponseDTO;
import com.example.hotel.service.WalletHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletHistoryController {
    private final WalletHistoryService walletHistoryService;

    public WalletHistoryController(WalletHistoryService walletHistoryService) {
        this.walletHistoryService = walletHistoryService;
    }

    @PostMapping(value = "/wallet-history")
    public ResponseEntity<Integer> add(@RequestBody WalletHistoryRequestDTO requestDTO) {
        Integer savedId = walletHistoryService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/wallet-history/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody WalletHistoryRequestDTO requestDTO) {
        walletHistoryService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/wallet-history/{id}")
    public ResponseEntity<WalletHistoryResponseDTO> getById(@PathVariable Integer id) {
        WalletHistoryResponseDTO responseDTO = walletHistoryService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/wallet-history")
    public ResponseEntity<Page<WalletHistoryResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<WalletHistoryResponseDTO> page = walletHistoryService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/wallet-history/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        walletHistoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
