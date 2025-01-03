package com.example.hotel.controller;

import com.example.hotel.model.creditTransfer.CreditTransferRequestDTO;
import com.example.hotel.model.creditTransfer.CreditTransferResponseDTO;
import com.example.hotel.model.creditTransfer.record.AcceptCreditRecord;
import com.example.hotel.model.creditTransfer.record.CreditTransferRecord;
import com.example.hotel.model.wallet.WalletResponseDTO;
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

    @PostMapping(value = "/credit-transfer/user")
    public ResponseEntity<CreditTransferResponseDTO> AddCreditForUser(@RequestBody CreditTransferRecord requestDTO) {
        CreditTransferResponseDTO responseDTO = creditTransferService.AddCreditForUser(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping(value = "/credit-transfer/accepted")
    public ResponseEntity<WalletResponseDTO> acceptCreditTransfer(@RequestBody AcceptCreditRecord requestDTO) {
        WalletResponseDTO walletResponseDTO = creditTransferService.acceptCreditTransfer(requestDTO);
        return ResponseEntity.ok(walletResponseDTO);
    }

    @PostMapping(value = "/credit-transfer/rejected")
    public ResponseEntity<Void> rejectCreditTransfer(@RequestBody AcceptCreditRecord requestDTO) {
        creditTransferService.rejectCreditTransfer(requestDTO);
        return ResponseEntity.noContent().build();
    }
}
