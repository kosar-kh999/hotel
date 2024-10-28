package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.creditTransfer.*;
import com.example.hotel.model.creditTransfer.enumuration.CreditTransferType;
import com.example.hotel.model.creditTransfer.record.CreditTransferRecord;
import com.example.hotel.model.user.User;
import com.example.hotel.model.user.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditTransferService {
    private final CreditTransferRepo creditTransferRepo;
    private final UserRepo userRepo;
    private final CreditTransferMapper creditTransferMapper;

    public CreditTransferService(CreditTransferRepo creditTransferRepo,
                                 UserRepo userRepo,
                                 CreditTransferMapper creditTransferMapper) {
        this.creditTransferRepo = creditTransferRepo;
        this.userRepo = userRepo;
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

    public CreditTransferResponseDTO AddCreditForUser(CreditTransferRecord requestDTO) {
        if (requestDTO.userId() != null && requestDTO.amount() != null && requestDTO.amount().doubleValue() > 0) {
            Optional<User> userOpt = userRepo.findById(requestDTO.userId());
            User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", requestDTO.userId())));
            CreditTransfer creditTransfer = new CreditTransfer();
            creditTransfer.setUser(user);
            creditTransfer.setAmount(requestDTO.amount());
            creditTransfer.setCreditTransferType(CreditTransferType.INITIAL_REQUEST);
            creditTransfer.setDescription(requestDTO.description());
            creditTransferRepo.save(creditTransfer);
            return creditTransferMapper.toDTO(creditTransfer);
        } else throw new CustomException("مبلغ واریزی به کیف پول باید بیشتر از صفر باشد", 232);
    }
}
