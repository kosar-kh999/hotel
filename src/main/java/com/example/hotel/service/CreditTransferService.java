package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.creditTransfer.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditTransferService {
    private final CreditTransferRepo creditTransferRepo;
    private final CreditTransferMapper creditTransferMapper;

    public CreditTransferService(CreditTransferRepo creditTransferRepo,
                                 CreditTransferMapper creditTransferMapper) {
        this.creditTransferRepo = creditTransferRepo;
        this.creditTransferMapper = creditTransferMapper;
    }

    public Integer save(CreditTransferRequestDTO requestDTO) {
        CreditTransfer creditTransfer = creditTransferMapper.toEntity(requestDTO);
        return creditTransferRepo.save(creditTransfer).getId();
    }

    public void update(Integer id, CreditTransferRequestDTO requestDTO) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(id);
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        creditTransferMapper.toEntity(requestDTO, creditTransfer);
        creditTransferRepo.save(creditTransfer);
    }

    public CreditTransferResponseDTO findById(Integer id) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(id);
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return creditTransferMapper.toDTO(creditTransfer);
    }

    public Page<CreditTransferResponseDTO> findAll(Pageable pageable) {
        Page<CreditTransfer> all = creditTransferRepo.findAll(pageable);
        return creditTransferMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(id);
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        creditTransferRepo.delete(creditTransfer);
    }
}
