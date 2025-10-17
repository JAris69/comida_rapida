package com.app.comidarapida.web.controller.rol;

import com.app.comidarapida.service.implementation.rol.RolService;
import com.app.comidarapida.web.dtos.roles.RolRequestDTO;
import com.app.comidarapida.web.dtos.roles.RolResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "04 - Roles", description = "Controlador de Roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @Operation(
            summary = "Listar todos los Roles",
            description = "Lista todos los roles.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful roles",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public List<RolResponseDTO> listarRoles() {
        return rolService.listarRoles();
    }

    @Operation(
            summary = "Crear Rol",
            description = "Crea un ROl para el sistema",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Solicitud de registrar rol",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RolRequestDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful Method Save",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class)
                            )
                    )
            }
    )
    @PostMapping
    public RolResponseDTO crearRol(@RequestBody RolRequestDTO request) {
        return rolService.crearRol(request);
    }

    @Operation(
            summary = "Actualiza un Rol por Id",
            description = "Actualiza un Rol por Id"
    )
    @PutMapping("/{id}")
    public RolResponseDTO actualizarRol(@PathVariable Integer id,
                                        @RequestBody RolRequestDTO request) {
        return rolService.actualizarRol(id, request);
    }

    @Operation(
            summary = "Listar los Roles por id",
            description = "Lista los roles por id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful roles",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public RolResponseDTO obtenerRolPorId(@PathVariable Integer id) {
        return rolService.obtenerRolPorId(id);
    }
}
