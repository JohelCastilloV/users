package com.sermaluc.user.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    @Schema(example = "123")
    private String id;
    @Schema(example = "2024-09-16T09:25:52.207Z")
    private LocalDateTime created;
    @Schema(example = "2024-09-16T09:25:52.207Z")
    private LocalDateTime modified;
    @Schema(example = "2024-09-16T09:25:52.207Z")
    private LocalDateTime lastLogin;
    @Schema(example = "2312sadasdq231adeq")
    private String token;
    @Schema(example = "true")
    private Boolean isActive;
}
