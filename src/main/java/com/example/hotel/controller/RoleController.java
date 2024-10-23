package com.example.hotel.controller;

import com.example.hotel.model.role.RoleRequestDTO;
import com.example.hotel.model.role.RoleResponseDTO;
import com.example.hotel.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping(value = "/role")
    public ResponseEntity<Integer> add(@RequestBody RoleRequestDTO requestDTO) {
        Integer savedId = roleService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody RoleRequestDTO requestDTO) {
        roleService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Integer id) {
        RoleResponseDTO responseDTO = roleService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/role")
    public ResponseEntity<Page<RoleResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<RoleResponseDTO> page = roleService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
