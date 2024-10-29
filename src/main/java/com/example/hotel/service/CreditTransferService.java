package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.creditTransfer.*;
import com.example.hotel.model.creditTransfer.enumuration.CreditTransferType;
import com.example.hotel.model.creditTransfer.record.AcceptCreditRecord;
import com.example.hotel.model.creditTransfer.record.CreditTransferRecord;
import com.example.hotel.model.user.User;
import com.example.hotel.model.user.UserRepo;
import com.example.hotel.model.wallet.Wallet;
import com.example.hotel.model.wallet.WalletMapper;
import com.example.hotel.model.wallet.WalletRepo;
import com.example.hotel.model.wallet.WalletResponseDTO;
import com.example.hotel.model.walletHistory.WalletHistory;
import com.example.hotel.model.walletHistory.WalletHistoryRepo;
import com.example.hotel.model.walletHistory.enumuration.TransactionType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CreditTransferService {
    private final CreditTransferRepo creditTransferRepo;
    private final UserRepo userRepo;
    private final WalletRepo walletRepo;
    private final WalletHistoryRepo walletHistoryRepo;
    private final CreditTransferMapper creditTransferMapper;
    private final WalletMapper walletMapper;

    public CreditTransferService(CreditTransferRepo creditTransferRepo,
                                 UserRepo userRepo,
                                 WalletRepo walletRepo,
                                 WalletHistoryRepo walletHistoryRepo,
                                 CreditTransferMapper creditTransferMapper,
                                 WalletMapper walletMapper) {
        this.creditTransferRepo = creditTransferRepo;
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
        this.walletHistoryRepo = walletHistoryRepo;
        this.creditTransferMapper = creditTransferMapper;
        this.walletMapper = walletMapper;
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
        } else throw new CustomException("مبلغ واریزی به کیف پول باید بیشتر از صفر باشد");
    }

    public void increaseCredit(AcceptCreditRecord record) {
        Optional<User> userOpt = userRepo.findById(record.userId());
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.userId())));
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(record.creditId());
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.creditId())));
        Wallet wallet = user.getWallet();
        wallet.setBalance(creditTransfer.getAmount());
        walletRepo.save(wallet);
    }
    @Transactional
    public WalletResponseDTO acceptCreditTransfer(AcceptCreditRecord record) {
        Optional<CreditTransfer> creditTransferOpt = creditTransferRepo.findById(record.creditId());
        CreditTransfer creditTransfer = creditTransferOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.creditId())));
        creditTransfer.setCreditTransferType(CreditTransferType.CONFIRMED);
        creditTransferRepo.save(creditTransfer);

        increaseCredit(record);

        WalletHistory walletHistory = new WalletHistory();
        walletHistory.setCredit(creditTransfer.getAmount());
        walletHistory.setWallet(creditTransfer.getUser().getWallet());
        walletHistory.setDescription(creditTransfer.getDescription());
        walletHistory.setTransactionDate(LocalDateTime.now());
        List<WalletHistory> walletHistories = creditTransfer.getUser().getWallet().getWalletHistories();
        walletHistories.add(walletHistory);
        walletHistory.setTransactionType(TransactionType.DEPOSIT);
        walletHistoryRepo.save(walletHistory);
        return walletMapper.toDTO(creditTransfer.getUser().getWallet());
    }
}
