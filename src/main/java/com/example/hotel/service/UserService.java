package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.role.Role;
import com.example.hotel.model.role.RoleMapper;
import com.example.hotel.model.role.RoleRepo;
import com.example.hotel.model.role.RoleRequestDTO;
import com.example.hotel.model.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo,
                       UserMapper userMapper,
                       RoleMapper roleMapper,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Integer save(UserRequestDTO requestDTO) {
        User user = userRepo.findByUsername(requestDTO.getUsername());
        if (user == null)
            throw new CustomException(String.format("کاربر  %s قبلا لاگین کرده است.", requestDTO.getUsername()));
        user.setPassword(bCryptPasswordEncoder.encode(requestDTO.getPassword()));
        Role role = roleRepo.findByCode(requestDTO.getRole().getCode());
        if (role != null) {
            Set<RoleRequestDTO> roles = new HashSet<>();
            RoleRequestDTO roleDto = new RoleRequestDTO();
            roleDto.setId(role.getId());
            roles.add(roleDto);
            requestDTO.setRoles(roles);
        }
        User newUser = userMapper.toEntity(requestDTO);
        newUser.setRoles(requestDTO.getRoles()
                .stream()
                .map(roleMapper::toEntity)
                .collect(Collectors.toSet()));
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
}
