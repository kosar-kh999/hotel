package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.role.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepo roleRepo, RoleMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }

    public Integer save(RoleRequestDTO requestDTO) {
        Role role = roleMapper.toEntity(requestDTO);
        return roleRepo.save(role).getId();
    }

    public void update(Integer id, RoleRequestDTO requestDTO) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roleMapper.toEntity(requestDTO, role);
        roleRepo.save(role);
    }

    public RoleResponseDTO findById(Integer id) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return roleMapper.toDTO(role);
    }

    public Page<RoleResponseDTO> findAll(Pageable pageable) {
        Page<Role> all = roleRepo.findAll(pageable);
        return roleMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roleRepo.delete(role);
    }
}
