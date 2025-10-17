package com.app.comidarapida.web.dtos.auth;

import com.app.comidarapida.web.dtos.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private String refreshToken;
    private UserResponseDTO user;
}
