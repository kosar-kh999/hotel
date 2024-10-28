package com.example.hotel.controller;

import com.example.hotel.model.user.UserRequestDTO;
import com.example.hotel.model.user.UserResponseDTO;
import com.example.hotel.model.user.record.ResetPasswordRecord;
import com.example.hotel.model.user.record.UserRecord;
import com.example.hotel.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDTO requestDTO) {
        Integer savedId = userService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UserRequestDTO requestDTO) {
        userService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Integer id) {
        UserResponseDTO responseDTO = userService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Page<UserResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<UserResponseDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/user/role")
    public ResponseEntity<Void> assignRoleForUser(@RequestBody UserRecord requestDTO) {
        userService.assignRoleForUser(requestDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/user/reset/password")
    public ResponseEntity<Void> resetPasswordOfUser(@RequestBody ResetPasswordRecord requestDTO) {
        userService.resetPasswordOfUser(requestDTO);
        return ResponseEntity.noContent().build();
    }
}
