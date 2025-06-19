package com.Auth.service.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
