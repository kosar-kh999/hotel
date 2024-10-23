package com.example.hotel.controller;

import com.example.hotel.model.room.RoomRequestDTO;
import com.example.hotel.model.room.RoomResponseDTO;
import com.example.hotel.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping(value = "/room")
    public ResponseEntity<Integer> add(@RequestBody RoomRequestDTO requestDTO) {
        Integer savedId = roomService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/room/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody RoomRequestDTO requestDTO) {
        roomService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/room/{id}")
    public ResponseEntity<RoomResponseDTO> getById(@PathVariable Integer id) {
        RoomResponseDTO responseDTO = roomService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/room")
    public ResponseEntity<Page<RoomResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<RoomResponseDTO> page = roomService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/room/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
