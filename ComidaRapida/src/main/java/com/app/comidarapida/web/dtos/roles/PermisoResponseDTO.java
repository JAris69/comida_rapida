package com.app.comidarapida.web.dtos.roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermisoResponseDTO {

    private Integer id;
    private String codigo;
    private String descripcion;
}
