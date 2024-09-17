package com.sermaluc.user.controller;

import com.sermaluc.user.controller.dto.UserRequestDTO;
import com.sermaluc.user.controller.dto.UserResponseDTO;
import com.sermaluc.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Register a user")
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO saveUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.saveUser(userRequestDTO);
    }
}
