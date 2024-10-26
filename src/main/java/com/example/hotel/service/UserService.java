package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.core.record.UserRecord;
import com.example.hotel.model.role.Role;
import com.example.hotel.model.role.RoleRepo;
import com.example.hotel.model.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo,
                       UserMapper userMapper
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
    }


    public Integer save(UserRequestDTO requestDTO) {
        User user = userRepo.findByUsername(requestDTO.getUsername());
        if (user != null)
            throw new CustomException(String.format("کاربر  %s قبلا ثبت نام کرده است.", requestDTO.getUsername()));
        User newUser = userMapper.toEntity(requestDTO);
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
}
