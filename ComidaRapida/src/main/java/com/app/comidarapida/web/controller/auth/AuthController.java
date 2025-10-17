package com.app.comidarapida.web.controller.auth;

import com.app.comidarapida.service.implementation.AuthServiceImpl.AuthServiceImpl;
import com.app.comidarapida.service.implementation.user.UserServiceImpl;
import com.app.comidarapida.web.dtos.auth.LoginResponseDTO;
import com.app.comidarapida.web.dtos.user.UserLoginRequestDTO;
import com.app.comidarapida.web.dtos.user.UserRegisterRequestDTO;
import com.app.comidarapida.web.dtos.user.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "01 - Authentication", description = "Controlador de Autenticacion")
public class AuthController {

    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;

    public AuthController(UserServiceImpl userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Operation(
            summary = "Register User",
            description = "Registra un usuario de acuerdo al personal seleccionado.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Solicitud de registrar usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRegisterRequestDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDTO.class)
                            )
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterRequestDTO request) {
        UserResponseDTO response = userService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Login User",
            description = "Autenticar un usuario y devolver el token de autenticación junto con los detalles del usuario.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Solicitud de autenticación con nombre de usuario y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserLoginRequestDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponseDTO.class)
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
