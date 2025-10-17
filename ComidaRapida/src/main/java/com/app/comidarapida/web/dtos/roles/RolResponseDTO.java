package com.app.comidarapida.web.dtos.roles;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolResponseDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String estado;
    private List<PermisoDTO> permisos;
}
