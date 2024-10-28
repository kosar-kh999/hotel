package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.wallet.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepo walletRepo;
    private final WalletMapper walletMapper;

    public WalletService(WalletRepo walletRepo,
                         WalletMapper walletMapper) {
        this.walletRepo = walletRepo;
        this.walletMapper = walletMapper;
    }

    public Integer save(WalletRequestDTO requestDTO) {
        Wallet wallet = walletMapper.toEntity(requestDTO);
        return walletRepo.save(wallet).getId();
    }

    public void update(Integer id, WalletRequestDTO requestDTO) {
        Optional<Wallet> walletOpt = walletRepo.findById(id);
        Wallet wallet = walletOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        walletMapper.toEntity(requestDTO, wallet);
        walletRepo.save(wallet);
    }

    public WalletResponseDTO findById(Integer id) {
        Optional<Wallet> walletOpt = walletRepo.findById(id);
        Wallet wallet = walletOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return walletMapper.toDTO(wallet);
    }

    public Page<WalletResponseDTO> findAll(Pageable pageable) {
        Page<Wallet> all = walletRepo.findAll(pageable);
        return walletMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<Wallet> walletOpt = walletRepo.findById(id);
        Wallet wallet = walletOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        walletRepo.delete(wallet);
    }
}
