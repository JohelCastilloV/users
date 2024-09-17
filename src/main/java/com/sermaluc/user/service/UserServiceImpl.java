package com.sermaluc.user.service;

import com.sermaluc.user.controller.dto.PhoneDTO;
import com.sermaluc.user.controller.dto.UserRequestDTO;
import com.sermaluc.user.controller.dto.UserResponseDTO;
import com.sermaluc.user.exception.UserAlreadyExistsException;
import com.sermaluc.user.mapper.PhoneMapper;
import com.sermaluc.user.mapper.UserMapper;
import com.sermaluc.user.model.Phone;
import com.sermaluc.user.model.Token;
import com.sermaluc.user.model.User;
import com.sermaluc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JWTService jwtService;

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        existingUser.ifPresent(user ->  {
           throw  new UserAlreadyExistsException("El correo ya registrado.");
        });
        LocalDateTime now = LocalDateTime.now();
        User user = userMapper.mapToEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setStatus(true);
        user.setUuid(UUID.randomUUID());
        String token = jwtService.generateToken(user);
        user.setToken(Token.builder().token(token).build());
        userRepository.save(user);
        UserResponseDTO userResponseDTO = userMapper.mapToDTO(user);
        return userResponseDTO;
    }
}
