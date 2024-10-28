package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.role.Role;
import com.example.hotel.model.role.RoleRepo;
import com.example.hotel.model.user.*;
import com.example.hotel.model.user.record.ResetPasswordRecord;
import com.example.hotel.model.user.record.UserRecord;
import com.example.hotel.model.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo,
                       UserMapper userMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Integer save(UserRequestDTO requestDTO) {
        User user = userRepo.findByUsername(requestDTO.getUsername());
        if (user != null)
            throw new CustomException(String.format("کاربر  %s قبلا ثبت نام کرده است.", requestDTO.getUsername()));
        User newUser = userMapper.toEntity(requestDTO);
        newUser.setPassword(bCryptPasswordEncoder.encode(requestDTO.getPassword()));
        return userRepo.save(newUser).getId();
    }

    public void update(Integer id, UserRequestDTO requestDTO) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userMapper.toEntity(requestDTO, user);
        userRepo.save(user);
    }

    public UserResponseDTO findById(Integer id) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return userMapper.toDTO(user);
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> all = userRepo.findAll(pageable);
        return userMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userRepo.delete(user);
    }

    public void assignRoleForUser(UserRecord userRecord) {
        Optional<User> userOpt = userRepo.findById(userRecord.userId());
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", userRecord.userId())));

        for (Integer roleId : userRecord.roleIds()) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", userRecord.roleIds())));
            user.getRoles().add(role);
        }
        userMapper.toDTO(userRepo.save(user));
    }

    public void resetPasswordOfUser(ResetPasswordRecord record) {
        Optional<User> userOpt = userRepo.findById(record.id());
        User user = userOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", record.id())));
        if (!(record.newPassword().equals(record.confirmedPassword())))
            throw new CustomException("رمز عبور وارد شده با تکرار رمز عبور آن برابر باشد.");
        user.setPassword(bCryptPasswordEncoder.encode(record.newPassword()));
        userRepo.save(user);
    }

    public void updateWalletOfUser() {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.getWallet() == null) {
                Wallet wallet = new Wallet();
                wallet.setBalance(BigDecimal.ZERO);
                wallet.setUser(user);
                user.setWallet(wallet);
            }
        }
        userRepo.saveAll(users);
    }
}
