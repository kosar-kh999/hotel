package com.example.hotel.model.creditTransfer;

import com.example.hotel.core.base.BaseMapper;
import com.example.hotel.model.user.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class CreditTransferMapper implements BaseMapper<CreditTransfer, CreditTransferRequestDTO, CreditTransferResponseDTO> {
    private final UserMapper userMapper;

    public CreditTransferMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public CreditTransfer toEntity(CreditTransferRequestDTO dto) {
        CreditTransfer creditTransfer = new CreditTransfer();
        toEntity(dto, creditTransfer);
        return creditTransfer;
    }

    @Override
    public void toEntity(CreditTransferRequestDTO dto, CreditTransfer creditTransfer) {
        creditTransfer.setDescription(dto.getDescription());
        creditTransfer.setAmount(dto.getAmount());
        creditTransfer.setCreditTransferType(dto.getCreditTransferType());
    }

    @Override
    public CreditTransferResponseDTO toDTO(CreditTransfer creditTransfer) {
        CreditTransferResponseDTO responseDTO = CreditTransferResponseDTO.builder().build();
        toDTO(creditTransfer, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(CreditTransfer creditTransfer, CreditTransferResponseDTO dto) {
        dto.setDescription(creditTransfer.getDescription());
        dto.setAmount(creditTransfer.getAmount());
        dto.setCreditTransferType(creditTransfer.getCreditTransferType());
        dto.setUser(userMapper.toDTO(creditTransfer.getUser()));
        baseField(dto, creditTransfer);
    }
}
