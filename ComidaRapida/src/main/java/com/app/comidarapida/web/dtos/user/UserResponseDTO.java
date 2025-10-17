package com.app.comidarapida.web.dtos.user;

import com.app.comidarapida.enums.Estado;
import com.app.comidarapida.enums.EstadoLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String correo;
    private String nombreUsuario;
    private Estado estado;
    private EstadoLog estadoLog;
    private List<String> roles;

    // Datos del personal asociado
    private String nombreCompleto;
    private String ci;
    private String telefono;
}
