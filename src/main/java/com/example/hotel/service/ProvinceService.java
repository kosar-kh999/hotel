package com.example.hotel.service;

import com.example.hotel.core.exception.CustomException;
import com.example.hotel.model.state.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService {
    private final ProvinceRepo provinceRepo;
    private final ProvinceMapper provinceMapper;

    public ProvinceService(ProvinceRepo provinceRepo,
                           ProvinceMapper provinceMapper) {
        this.provinceRepo = provinceRepo;
        this.provinceMapper = provinceMapper;
    }

    public Integer save(ProvinceRequestDTO requestDTO) {
        Province province = provinceMapper.toEntity(requestDTO);
        return provinceRepo.save(province).getId();
    }

    public void update(Integer id, ProvinceRequestDTO requestDTO) {
        Optional<Province> provinceOpt = provinceRepo.findById(id);
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        provinceMapper.toEntity(requestDTO, province);
        provinceRepo.save(province);
    }

    public ProvinceResponseDTO findById(Integer id) {
        Optional<Province> provinceOpt = provinceRepo.findById(id);
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return provinceMapper.toDTO(province);
    }

    public Page<ProvinceResponseDTO> findAll(Pageable pageable) {
        Page<Province> all = provinceRepo.findAll(pageable);
        return provinceMapper.toDTO(all);
    }

    public void delete(Integer id) {
        Optional<Province> provinceOpt = provinceRepo.findById(id);
        Province province = provinceOpt.orElseThrow(() -> new CustomException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        provinceRepo.delete(province);
    }
}
