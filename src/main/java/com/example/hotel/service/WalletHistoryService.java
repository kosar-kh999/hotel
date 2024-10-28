package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.walletHistory.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletHistoryService {
    private final WalletHistoryRepo walletHistoryRepo;
    private final WalletHistoryMapper walletHistoryMapper;

    public WalletHistoryService(WalletHistoryRepo walletHistoryRepo,
                                WalletHistoryMapper walletHistoryMapper) {
        this.walletHistoryRepo = walletHistoryRepo;
        this.walletHistoryMapper = walletHistoryMapper;
    }

    public Integer save(WalletHistoryRequestDTO requestDTO) {
        WalletHistory walletHistory = walletHistoryMapper.toEntity(requestDTO);
        return walletHistoryRepo.save(walletHistory).getId();
    }

    public void update(Integer id, WalletHistoryRequestDTO requestDTO) {
        Optional<WalletHistory> walletHistoryOpt = walletHistoryRepo.findById(id);
        WalletHistory walletHistory = walletHistoryOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        walletHistoryMapper.toEntity(requestDTO, walletHistory);
        walletHistoryRepo.save(walletHistory);
    }

    public WalletHistoryResponseDTO findById(Integer id) {
        Optional<WalletHistory> walletHistoryOpt = walletHistoryRepo.findById(id);
        WalletHistory walletHistory = walletHistoryOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return walletHistoryMapper.toDTO(walletHistory);
    }

    public Page<WalletHistoryResponseDTO> findAll(Pageable pageable) {
        Page<WalletHistory> all = walletHistoryRepo.findAll(pageable);
        return walletHistoryMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<WalletHistory> walletHistoryOpt = walletHistoryRepo.findById(id);
        WalletHistory walletHistory = walletHistoryOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        walletHistoryRepo.delete(walletHistory);
    }
}
