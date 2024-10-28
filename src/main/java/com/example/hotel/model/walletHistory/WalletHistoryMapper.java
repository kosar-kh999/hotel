package com.example.hotel.model.walletHistory;

import com.example.hotel.core.base.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class WalletHistoryMapper implements BaseMapper<WalletHistory, WalletHistoryRequestDTO, WalletHistoryResponseDTO> {
    @Override
    public WalletHistory toEntity(WalletHistoryRequestDTO dto) {
        WalletHistory walletHistory = new WalletHistory();
        toEntity(dto, walletHistory);
        return walletHistory;
    }

    @Override
    public void toEntity(WalletHistoryRequestDTO dto, WalletHistory walletHistory) {
        walletHistory.setDescription(dto.getDescription());
        walletHistory.setCredit(dto.getCredit());
        walletHistory.setTransactionType(dto.getTransactionType());
    }

    @Override
    public WalletHistoryResponseDTO toDTO(WalletHistory walletHistory) {
        WalletHistoryResponseDTO responseDTO = WalletHistoryResponseDTO.builder().build();
        toDTO(walletHistory, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(WalletHistory walletHistory, WalletHistoryResponseDTO dto) {
        dto.setDescription(walletHistory.getDescription());
        dto.setCredit(walletHistory.getCredit());
        dto.setTransactionType(walletHistory.getTransactionType());
        baseField(dto, walletHistory);
    }
}
