package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    public Integer save(UserRequestDTO requestDTO) {
        User user = userMapper.toEntity(requestDTO);
        return userRepo.save(user).getId();
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
}
