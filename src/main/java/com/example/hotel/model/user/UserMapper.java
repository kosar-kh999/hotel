package com.example.hotel.model.user;

import com.example.hotel.core.base.BaseMapper;
import com.example.hotel.model.wallet.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserMapper implements BaseMapper<User, UserRequestDTO, UserResponseDTO> {

    @Override
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        toEntity(dto, user);
        return user;
    }

    @Override
    public void toEntity(UserRequestDTO dto, User user) {
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setUser(user);
        user.setWallet(wallet);
    }

    @Override
    public UserResponseDTO toDTO(User user) {
        UserResponseDTO responseDTO = UserResponseDTO.builder().build();
        toDTO(user, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(User user, UserResponseDTO dto) {
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        baseField(dto, user);
    }
}
