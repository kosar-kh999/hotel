package com.example.hotel.model.wallet;

import com.example.hotel.core.base.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper implements BaseMapper<Wallet, WalletRequestDTO, WalletResponseDTO> {
    @Override
    public Wallet toEntity(WalletRequestDTO dto) {
        Wallet wallet = new Wallet();
        toEntity(dto, wallet);
        return wallet;
    }

    @Override
    public void toEntity(WalletRequestDTO dto, Wallet wallet) {
        wallet.setBalance(dto.getBalance());
    }

    @Override
    public WalletResponseDTO toDTO(Wallet wallet) {
        WalletResponseDTO responseDTO = WalletResponseDTO.builder().build();
        toDTO(wallet, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Wallet wallet, WalletResponseDTO dto) {
        dto.setBalance(wallet.getBalance());
        baseField(dto, wallet);
    }
}
