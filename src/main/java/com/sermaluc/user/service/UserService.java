package com.sermaluc.user.service;

import com.sermaluc.user.controller.dto.UserRequestDTO;
import com.sermaluc.user.controller.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);
}
