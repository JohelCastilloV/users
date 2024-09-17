package com.sermaluc.user.controller.dto;

import com.sermaluc.user.validator.PasswordPattern;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    @Schema(example = "Juan Rodriguez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(example = "juan@dominio.cl",  requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = "^.+?@dominio.cl$", message = "correo de formato invalido")
    private String email;
    @Schema(example = "hunter123", requiredMode = Schema.RequiredMode.REQUIRED)
    @PasswordPattern(message = "formato de password invalido")
    private String password;
    private List<PhoneDTO> phones;
}
